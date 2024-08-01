package io.waterkite94.stalk.api.controller

import io.waterkite94.stalk.api.dto.request.CreatePostRequest
import io.waterkite94.stalk.api.dto.request.DeletePostRequest
import io.waterkite94.stalk.api.dto.response.ApiResponse
import io.waterkite94.stalk.api.dto.response.CreatePostResponse
import io.waterkite94.stalk.application.usecase.CreatePost
import io.waterkite94.stalk.application.usecase.DeletePost
import io.waterkite94.stalk.application.usecase.FindPost
import io.waterkite94.stalk.domain.model.vo.BoardPost
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/posts")
class PostRestController(
    private val createPost: CreatePost,
    private val deletePost: DeletePost,
    private val findPost: FindPost
) {
    @PostMapping
    fun createPostApi(
        @RequestBody request: CreatePostRequest
    ): ApiResponse<CreatePostResponse> {
        val createdPost = createPost.createPost(request.toDomain())

        return ApiResponse.success(CreatePostResponse.toResponse(createdPost))
    }

    @DeleteMapping
    fun deletePostApi(
        @RequestBody request: DeletePostRequest
    ): ApiResponse<String> {
        deletePost.deletePost(request.postId, request.memberId)

        return ApiResponse.success("Deleted post successfully")
    }

    @GetMapping
    fun getPostsApi(
        @RequestParam offset: Int,
        @RequestParam limit: Int
    ): ApiResponse<List<BoardPost>> {
        val findPosts = findPost.findBoardPosts(offset, limit)

        return ApiResponse.success(findPosts)
    }
}
