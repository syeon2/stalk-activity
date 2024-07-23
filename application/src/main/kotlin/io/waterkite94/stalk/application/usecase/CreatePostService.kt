package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.port.PostPersistencePort
import io.waterkite94.stalk.domain.model.vo.Post
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreatePostService(
    private val postPersistencePort: PostPersistencePort
) : CreatePost {
    override fun createPost(post: Post): Post = postPersistencePort.save(initializePost(post))

    private fun initializePost(post: Post): Post = post.withPostId(generateId())

    private fun generateId(): String = UUID.randomUUID().toString()
}
