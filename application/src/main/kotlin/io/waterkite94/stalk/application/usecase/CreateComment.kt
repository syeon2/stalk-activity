package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.domain.model.vo.Comment

interface CreateComment {
    fun createComment(comment: Comment): Comment
}
