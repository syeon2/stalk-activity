package io.waterkite94.stalk.application.service

import io.waterkite94.stalk.application.IntegrationTestSupport
import io.waterkite94.stalk.application.client.MemberDto
import io.waterkite94.stalk.domain.model.vo.BoardComment
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.InjectMocks
import org.mockito.Mockito.verify
import java.time.LocalDateTime

class FindCommentServiceTest : IntegrationTestSupport() {
    @InjectMocks
    lateinit var findCommentService: FindCommentService

    @Test
    fun findCommentByPostId() {
        // given
        val postId = "postId"
        val offset = 0
        val limit = 10
        val listOf = listOf(BoardComment("article", null, "memberId"))

        given(commentPersistencePort.findCommentsByPostId(postId, offset, limit)).willReturn(listOf)
        given(memberServiceClient.getMember("memberId")).willReturn(createMemberDto())

        // when
        findCommentService.findCommentByPostId(postId, offset, limit)

        // then
        verify(commentPersistencePort, times(1)).findCommentsByPostId(postId, offset, limit)
        verify(memberServiceClient, times(listOf.size)).getMember("memberId")
    }

    private fun createMemberDto() =
        MemberDto(
            1L,
            "memberId",
            "username",
            "email",
            "password",
            "00011112222",
            "introduction",
            null,
            LocalDateTime.now(),
            LocalDateTime.now()
        )
}
