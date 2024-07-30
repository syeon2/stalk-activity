package io.waterkite94.stalk.api.controller

import io.waterkite94.stalk.api.ControllerTestSupport
import io.waterkite94.stalk.api.dto.request.LikeCommentRequest
import io.waterkite94.stalk.api.dto.request.LikePostRequest
import io.waterkite94.stalk.application.usecase.ContentLike
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@WebMvcTest(controllers = [LikeRestController::class])
class LikeRestControllerTest : ControllerTestSupport() {
    @MockBean
    private lateinit var contentLike: ContentLike

    @Test
    @DisplayName(value = "게시글 좋아요 API를 호출합니다.")
    fun likePostApi() {
        // given
        val memberId = "memberId"
        val postId = "postId"
        val request = LikePostRequest(memberId, postId)

        // when  // then
        mockMvc
            .perform(
                post("/api/v1/likes/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").value("Like Post successfully"))
    }

    @Test
    @DisplayName(value = "게시글 좋아요 취소 API를 호출합니다.")
    fun unlikePostApi() {
        // given
        val memberId = "memberId"
        val postId = "postId"
        val request = LikePostRequest(memberId, postId)

        // when  // then
        mockMvc
            .perform(
                delete("/api/v1/likes/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").value("Unlike Post successfully"))
    }

    @Test
    @DisplayName(value = "댓글 좋아요 API를 호출합니다.")
    fun likeCommentApi() {
        // given
        val memberId = "memberId"
        val commentId = "commentId"
        val request = LikeCommentRequest(memberId, commentId)

        // when  // then
        mockMvc
            .perform(
                post("/api/v1/likes/comment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").value("Like Comment successfully"))
    }

    @Test
    @DisplayName(value = "댓글 좋아요 취소 API를 호출합니다.")
    fun unlikeCommentApi() {
        // given
        val memberId = "memberId"
        val commentId = "commentid"
        val request = LikeCommentRequest(memberId, commentId)

        // when  // then
        mockMvc
            .perform(
                delete("/api/v1/likes/comment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").value("Unlike Comment successfully"))
    }
}
