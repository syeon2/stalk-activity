package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.domain.model.vo.BoardPost

interface FindPost {
    fun findBoardPosts(
        offset: Int,
        limit: Int
    ): List<BoardPost>
}
