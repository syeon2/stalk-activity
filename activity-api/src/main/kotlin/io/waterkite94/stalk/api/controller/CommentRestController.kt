package io.waterkite94.stalk.api.controller

import io.waterkite94.stalk.api.dto.request.CreateCommentRequest
import io.waterkite94.stalk.api.dto.request.DeleteCommentRequest
import io.waterkite94.stalk.api.dto.response.ApiResponse
import io.waterkite94.stalk.application.usecase.CreateComment
import io.waterkite94.stalk.application.usecase.DeleteComment
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class CommentRestController(
    private val createComment: CreateComment,
    private val deleteComment: DeleteComment
) {
    @PostMapping("/comments")
    fun createCommentRequest(
        @RequestBody request: CreateCommentRequest
    ): ApiResponse<String> {
        val createdComment = createComment.createComment(request.toDomain())

        return ApiResponse.success(createdComment.commentId)
    }

    @DeleteMapping("/comments")
    fun deleteCommentRequest(
        @RequestBody request: DeleteCommentRequest
    ): ApiResponse<String> {
        deleteComment.deleteComment(request.commentId, request.memberId)

        return ApiResponse.success("Deleted comment")
    }
}
