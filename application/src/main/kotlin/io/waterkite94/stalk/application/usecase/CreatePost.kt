package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.domain.model.vo.Post

interface CreatePost {
    fun createPost(post: Post): Post
}
