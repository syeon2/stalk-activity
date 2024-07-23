package io.waterkite94.stalk.application.port

import io.waterkite94.stalk.domain.model.vo.Post

interface PostPersistencePort {
    fun save(post: Post): Post

    fun findPostByPostId(postId: String): Post?

    fun deleteByPostId(postId: String)
}
