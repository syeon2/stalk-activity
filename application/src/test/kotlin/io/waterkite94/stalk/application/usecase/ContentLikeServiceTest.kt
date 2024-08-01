package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.IntegrationTestSupport
import io.waterkite94.stalk.domain.model.vo.CommentLike
import io.waterkite94.stalk.domain.model.vo.PostLike
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class ContentLikeServiceTest : IntegrationTestSupport() {
    @InjectMocks
    lateinit var contentLikeService: ContentLikeService

    @Test
    @DisplayName(value = "게시글을 '좋아요' 합니다.")
    fun likePost() {
        // given
        val memberId = "memberId"
        val postId = "postId"
        val postLike = PostLike(memberId = memberId, postId = postId)

        doNothing().`when`(likePersistencePort).savePostLike(postLike)

        // when
        contentLikeService.likePost(memberId, postId)

        // then
        verify(likePersistencePort, times(1)).savePostLike(postLike)
    }

    @Test
    @DisplayName(value = "게시글 좋아요를 취소합니다.")
    fun unlikePost() {
        // given
        val memberId = "memberId"
        val postId = "postId"

        doNothing().`when`(likePersistencePort).deletePostLike(memberId, postId)

        // when
        contentLikeService.unlikePost(memberId, postId)

        // then
        verify(likePersistencePort, times(1)).deletePostLike(memberId, postId)
    }

    @Test
    @DisplayName(value = "댓글을 '좋아요' 합니다.")
    fun likeComment() {
        // given
        val memberId = "memberId"
        val commentId = "commentId"
        val commentLike = CommentLike(memberId = memberId, commentId = commentId)

        doNothing().`when`(likePersistencePort).saveCommentLike(commentLike)

        // when
        contentLikeService.likeComment(memberId, commentId)

        // then
        verify(likePersistencePort, times(1)).saveCommentLike(commentLike)
    }

    @Test
    @DisplayName(value = "댓글 좋아요를 취소합니다.")
    fun unlikeComment() {
        // given
        val memberId = "memberId"
        val commentId = "commentId"

        doNothing().`when`(likePersistencePort).deleteCommentLike(memberId, commentId)

        // when
        contentLikeService.unlikeComment(memberId, commentId)

        // then
        verify(likePersistencePort, times(1)).deleteCommentLike(memberId, commentId)
    }
}
