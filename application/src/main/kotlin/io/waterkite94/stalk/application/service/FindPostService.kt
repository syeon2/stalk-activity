package io.waterkite94.stalk.application.service

import io.waterkite94.stalk.application.client.MemberServiceClient
import io.waterkite94.stalk.application.port.PostPersistencePort
import io.waterkite94.stalk.application.usecase.FindPost
import io.waterkite94.stalk.domain.model.vo.BoardPost
import org.springframework.stereotype.Service

@Service
class FindPostService(
    private val postPersistencePort: PostPersistencePort,
    private val memberServiceClient: MemberServiceClient
) : FindPost {
    override fun findBoardPosts(
        stockId: String,
        offset: Int,
        limit: Int
    ): List<BoardPost> {
        val findBoardPosts = postPersistencePort.findBoardPosts(stockId, offset, limit)

        val ansBoardPost: MutableList<BoardPost> = ArrayList()

        for (boardPost in findBoardPosts) {
            val member = memberServiceClient.getMember(boardPost.memberId!!)

            ansBoardPost.add(boardPost.withUsername(member.username))
        }

        return ansBoardPost
    }
}
