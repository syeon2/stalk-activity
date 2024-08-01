package io.waterkite94.stalk.api.controller

import io.waterkite94.stalk.api.dto.request.CreateCommentRequest
import io.waterkite94.stalk.api.dto.request.DeleteCommentRequest
import io.waterkite94.stalk.api.dto.response.ApiResponse
import io.waterkite94.stalk.application.usecase.CreateComment
import io.waterkite94.stalk.application.usecase.DeleteComment
import io.waterkite94.stalk.application.usecase.FindComment
import io.waterkite94.stalk.domain.model.vo.CommentDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/comments")
class CommentRestController(
    private val createComment: CreateComment,
    private val deleteComment: DeleteComment,
    private val findComment: FindComment
) {
    @PostMapping
    fun createCommentRequest(
        @RequestBody request: CreateCommentRequest
    ): ApiResponse<String> {
        val createdComment = createComment.createComment(request.toDomain())

        return ApiResponse.success(createdComment.commentId)
    }

    @DeleteMapping
    fun deleteCommentRequest(
        @RequestBody request: DeleteCommentRequest
    ): ApiResponse<String> {
        deleteComment.deleteComment(request.commentId, request.memberId)

        return ApiResponse.success("Deleted comment successfully")
    }

    @GetMapping
    fun findCommentsByPostIdApi(
        @RequestParam postId: String,
        @RequestParam offset: Int,
        @RequestParam limit: Int
    ): ApiResponse<List<CommentDto>> {
        val comments: List<CommentDto> = findComment.findCommentByPostId(postId, offset, limit)

        return ApiResponse.success(comments)
    }
}
