package io.waterkite94.stalk.api.controller

import io.waterkite94.stalk.api.dto.request.LikeCommentRequest
import io.waterkite94.stalk.api.dto.request.LikePostRequest
import io.waterkite94.stalk.api.dto.response.ApiResponse
import io.waterkite94.stalk.application.usecase.ContentLike
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/likes")
class LikeRestController(
    private val contentLike: ContentLike
) {
    @PostMapping("/post")
    fun likePostApi(
        @RequestBody request: LikePostRequest
    ): ApiResponse<String> {
        contentLike.likePost(request.memberId, request.postId)

        return ApiResponse.success("Like Post successfully")
    }

    @DeleteMapping("/post")
    fun unlikePostApi(
        @RequestBody request: LikePostRequest
    ): ApiResponse<String> {
        contentLike.unlikePost(request.memberId, request.postId)

        return ApiResponse.success("Unlike Post successfully")
    }

    @PostMapping("/comment")
    fun likeCommentApi(
        @RequestBody request: LikeCommentRequest
    ): ApiResponse<String> {
        contentLike.likeComment(request.memberId, request.commentId)

        return ApiResponse.success("Like Comment successfully")
    }

    @DeleteMapping("/comment")
    fun unlikeCommentApi(
        @RequestBody request: LikeCommentRequest
    ): ApiResponse<String> {
        contentLike.unlikeComment(request.memberId, request.commentId)

        return ApiResponse.success("Unlike Comment successfully")
    }
}
