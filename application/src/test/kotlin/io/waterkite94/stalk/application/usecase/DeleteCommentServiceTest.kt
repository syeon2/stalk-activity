package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.domain.model.vo.Comment
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.times
import org.mockito.InjectMocks
import org.mockito.Mockito.verify
import java.time.LocalDateTime

class DeleteCommentServiceTest : IntegrationTestSupport() {
    @InjectMocks
    private lateinit var deleteCommentService: DeleteCommentService

    @Test
    @DisplayName(value = "댓글 아이디와 회원 아이디를 매개변수로 받아 댓굴 삭제 메서드를 호출합니다.")
    fun deleteComment() {
        // given
        val commentId = "commentId"
        val memberId = "memberId"
        val comment = createCommentDto(commentId, memberId)

        given(commentPersistencePort.findByCommentId(commentId)).willReturn(comment)

        // when  // then
        assertThatCode { deleteCommentService.deleteComment(commentId, memberId) }.doesNotThrowAnyException()
        verify(commentPersistencePort, times(1)).deleteByCommentId(commentId)
    }

    @Test
    @DisplayName(value = "삭제하는 댓글의 아이디를 통해 댓글 정보를 조회하여 null이 발생하면 예외를 반환합니다.")
    fun deleteComment_commentNotFound() {
        // given
        val commentId = "commentId"
        val memberId = "memberId"

        given(commentPersistencePort.findByCommentId(commentId)).willReturn(null)

        // when
        val exception = catchThrowable { deleteCommentService.deleteComment(commentId, memberId) }

        // then
        assertThat(exception)
            .isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Comment with id $commentId not found")

        verify(commentPersistencePort, never()).deleteByCommentId(commentId)
    }

    @Test
    @DisplayName(value = "댓글 저자와 삭제하는 유저의 아이디가 일치하지 않으면 예외를 반환합니다.")
    fun deleteComment_permissionDenied() {
        // given
        val commentId = "commentId"
        val memberId = "memberId"
        val comment = createCommentDto(commentId, memberId)

        given(commentPersistencePort.findByCommentId(commentId)).willReturn(comment)

        // when
        val exception = catchThrowable { deleteCommentService.deleteComment(commentId, "difference_memberId") }

        // then
        assertThat(exception)
            .isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Permission denied: Only the comment author can delete this comment")

        verify(commentPersistencePort, never()).deleteByCommentId(commentId)
    }

    private fun createCommentDto(
        commentId: String,
        memberId: String
    ) = Comment(null, commentId, "article", "postId", memberId, LocalDateTime.now(), LocalDateTime.now())
}
