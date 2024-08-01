package io.waterkite94.stalk.application.service

import io.waterkite94.stalk.application.port.PostPersistencePort
import io.waterkite94.stalk.application.usecase.DeletePost
import org.springframework.stereotype.Service

@Service
class DeletePostService(
    private val postPersistencePort: PostPersistencePort
) : DeletePost {
    override fun deletePost(
        postId: String,
        memberId: String
    ) {
        // 회원 아이디와 일치하는지 확인
        validateAuthorMatch(postId, memberId)

        // 게시글 삭제
        postPersistencePort.deleteByPostId(postId)
    }

    private fun validateAuthorMatch(
        postId: String,
        memberId: String
    ) {
        val findPost = postPersistencePort.findPostByPostId(postId)

        if (findPost == null) {
            throw RuntimeException("Post with id $postId not found")
        } else {
            if (memberId != findPost.memberId) {
                throw RuntimeException("Permission denied: Only the post author can delete this post")
            }
        }
    }
}
