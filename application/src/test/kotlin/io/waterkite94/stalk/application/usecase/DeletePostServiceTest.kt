package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.IntegrationTestSupport
import io.waterkite94.stalk.application.service.DeletePostService
import io.waterkite94.stalk.domain.model.vo.Post
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

class DeletePostServiceTest : IntegrationTestSupport() {
    @InjectMocks
    private lateinit var deletePostService: DeletePostService

    @Test
    @DisplayName(value = "게시글 아이디와 회원 아이디를 파라미터로 받아 게시글 삭제 메서드를 호출합니다.")
    fun deletePost() {
        // given
        val postId = "postId"
        val memberId = "memberId"
        val stockId = "stockId"
        val post = createPost(stockId, postId, memberId)

        given(postPersistencePort.findPostByPostId(postId)).willReturn(post)

        // when // then
        assertThatCode { deletePostService.deletePost(postId, memberId) }.doesNotThrowAnyException()
        verify(postPersistencePort, times(1)).deleteByPostId(postId)
    }

    @Test
    @DisplayName(value = "삭제하는 게시글의 아이디를 통해 게시글 정보를 조회하여 null이 발생하면 예외를 반환합니다.")
    fun deletePost_postNotFound() {
        // given
        val postId = "postId"
        val memberId = "memberId"

        given(postPersistencePort.findPostByPostId(postId)).willReturn(null)

        // when
        val exception = catchThrowable { deletePostService.deletePost(postId, memberId) }

        // then
        assertThat(exception)
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("Post with id $postId not found")

        verify(postPersistencePort, never()).deleteByPostId(postId)
    }

    @Test
    @DisplayName(value = "게시글 저자와 삭제하는 유저의 아이디가 일치하지 않으면 예외를 반환합니다.")
    fun deletePost_permissionDenied() {
        // given
        val stockId = "stockId"
        val postId = "postId"
        val memberId = "memberId"
        val post = createPost(stockId, postId, memberId)

        given(postPersistencePort.findPostByPostId(postId)).willReturn(post)

        // when
        val exception = catchThrowable { deletePostService.deletePost(postId, "difference_memberId") }

        // then
        assertThat(exception)
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("Permission denied: Only the post author can delete this post")

        verify(postPersistencePort, never()).deleteByPostId(postId)
    }

    private fun createPost(
        stockId: String,
        postId: String,
        memberId: String
    ): Post = Post(null, postId, "title", "article", null, null, memberId, stockId)
}
