package io.waterkite94.stalk.api.controller

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import io.waterkite94.stalk.api.ControllerTestSupport
import io.waterkite94.stalk.api.dto.request.LikeCommentRequest
import io.waterkite94.stalk.api.dto.request.LikePostRequest
import io.waterkite94.stalk.application.usecase.ContentLike
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
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
            .andDo(
                document(
                    "postlike-create",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("postId").type(JsonFieldType.STRING).description("게시글 아이디"),
                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디")
                    ),
                    responseFields(
                        fieldWithPath("data").type(JsonFieldType.STRING).description("성공 여부 메시지")
                    )
                )
            )
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
            .andDo(
                document(
                    "postlike-delete",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("postId").type(JsonFieldType.STRING).description("게시글 아이디"),
                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디")
                    ),
                    responseFields(
                        fieldWithPath("data").type(JsonFieldType.STRING).description("성공 여부 메시지")
                    )
                )
            )
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
            .andDo(
                document(
                    "commentlike-create",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("commentId").type(JsonFieldType.STRING).description("댓글 아이디"),
                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디")
                    ),
                    responseFields(
                        fieldWithPath("data").type(JsonFieldType.STRING).description("성공 여부 메시지")
                    )
                )
            )
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
            .andDo(
                document(
                    "commentlike-delete",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("commentId").type(JsonFieldType.STRING).description("댓글 아이디"),
                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디")
                    ),
                    responseFields(
                        fieldWithPath("data").type(JsonFieldType.STRING).description("성공 여부 메시지")
                    )
                )
            )
    }
}
