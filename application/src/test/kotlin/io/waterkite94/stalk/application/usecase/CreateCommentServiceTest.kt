package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.IntegrationTestSupport
import io.waterkite94.stalk.application.service.CreateCommentService
import io.waterkite94.stalk.domain.model.vo.Comment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor

class CreateCommentServiceTest : IntegrationTestSupport() {
    @InjectMocks
    private lateinit var createCommentService: CreateCommentService

    @Test
    @DisplayName(value = "댓글의 commentId를 생성하고 Repository에 전달합니다.")
    fun createComment() {
        // given
        val comment = createCommentDto()

        given(commentPersistencePort.save(any())).willAnswer { invocation ->
            invocation.arguments[0] as Comment
        }

        // when
        createCommentService.createComment(comment)

        // then
        val commentCaptor = argumentCaptor<Comment>()
        verify(commentPersistencePort).save(commentCaptor.capture())

        val captureComment = commentCaptor.firstValue

        assertThat(captureComment)
            .extracting("article", "postId", "memberId")
            .contains(comment.article, comment.postId, comment.memberId)
    }

    private fun createCommentDto() = Comment(null, null, "article", "postId", "memberId", null, null)
}
