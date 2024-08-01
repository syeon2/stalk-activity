package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.IntegrationTestSupport
import io.waterkite94.stalk.domain.model.vo.BoardPost
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.doNothing
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.kotlin.any

class FindPostServiceTest : IntegrationTestSupport() {
    @InjectMocks
    private lateinit var findPostService: FindPostService

    @Test
    fun findBoardPosts() {
        // given
        val offset = 0
        val limit = 10
        val boardPost = BoardPost("title", "article", "username", 7, 7, true)
        given(findPostService.findBoardPosts(offset, limit)).willReturn(listOf(boardPost))
        doNothing().`when`(memberServiceClient.getMember(any()))

        // when
        val boardPosts = findPostService.findBoardPosts(offset, limit)

        // then
        assertThat(boardPosts).isNotEmpty()
        assertThat(boardPosts).hasSize(1)
    }
}
