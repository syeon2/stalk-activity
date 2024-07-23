package io.waterkite94.stalk.api.controller

import io.waterkite94.stalk.api.ControllerTestSupport
import io.waterkite94.stalk.api.dto.request.CreateCommentRequest
import io.waterkite94.stalk.api.dto.request.DeleteCommentRequest
import io.waterkite94.stalk.application.usecase.CreateComment
import io.waterkite94.stalk.application.usecase.DeleteComment
import io.waterkite94.stalk.domain.model.vo.Comment
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(controllers = [CommentRestController::class])
class CommentRestControllerTest : ControllerTestSupport() {
    @MockBean
    private lateinit var createComment: CreateComment

    @MockBean
    private lateinit var deleteComment: DeleteComment

    @Autowired
    private lateinit var commentRestController: CommentRestController

    @Test
    @DisplayName("댓글을 생성하는 API를 호출합니다.")
    fun createCommentApi() {
        // given
        val request = createCommentRequest()
        val result = createCommentDto()

        given(createComment.createComment(request.toDomain()))
            .willReturn(result)

        // when  // then
        mockMvc
            .perform(
                post("/api/v1/comments")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isString)
    }

    @Test
    fun deleteCommentApi() {
        // given
        val deleteCommentRequest = deleteCommentRequest()
        doNothing().`when`(deleteComment).deleteComment(deleteCommentRequest.commentId, deleteCommentRequest.memberId)

        // when  // then
        mockMvc
            .perform(
                delete("/api/v1/comments")
                    .content(objectMapper.writeValueAsString(deleteCommentRequest))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isString)
    }

    private fun createCommentRequest() = CreateCommentRequest("article", "postId", "memberId")

    private fun deleteCommentRequest() = DeleteCommentRequest("commentId", "memberId")

    private fun createCommentDto() =
        Comment(1L, "commentId", "article", "postId", "memberId", LocalDateTime.now(), LocalDateTime.now())
}
