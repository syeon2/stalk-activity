package io.waterkite94.stalk.api.controller

import io.waterkite94.stalk.api.dto.request.CreatePostRequest
import io.waterkite94.stalk.api.dto.response.ApiResponse
import io.waterkite94.stalk.api.dto.response.CreatePostResponse
import io.waterkite94.stalk.application.usecase.CreatePost
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class PostRestController(
    private val createPost: CreatePost
) {
    @PostMapping("/posts")
    fun createPostApi(
        @RequestBody request: CreatePostRequest
    ): ApiResponse<CreatePostResponse> {
        val createdPost = createPost.createPost(request.toDomain())

        return ApiResponse.success(CreatePostResponse.toResponse(createdPost))
    }
}
