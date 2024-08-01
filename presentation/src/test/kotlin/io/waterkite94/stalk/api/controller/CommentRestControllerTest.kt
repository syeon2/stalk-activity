package io.waterkite94.stalk.api.controller

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import io.waterkite94.stalk.api.ControllerTestSupport
import io.waterkite94.stalk.api.dto.request.CreateCommentRequest
import io.waterkite94.stalk.api.dto.request.DeleteCommentRequest
import io.waterkite94.stalk.application.usecase.CreateComment
import io.waterkite94.stalk.application.usecase.DeleteComment
import io.waterkite94.stalk.application.usecase.FindComment
import io.waterkite94.stalk.domain.model.vo.BoardComment
import io.waterkite94.stalk.domain.model.vo.Comment
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.doNothing
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.queryParameters
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

    @MockBean
    private lateinit var findComment: FindComment

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
            .andDo(
                document(
                    "comment-create",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("article").type(JsonFieldType.STRING).description("댓글 내용"),
                        fieldWithPath("postId").type(JsonFieldType.STRING).description("게시글 아이디"),
                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("작성자 아이디")
                    ),
                    responseFields(
                        fieldWithPath("data").type(JsonFieldType.STRING).description("댓글 아이디")
                    )
                )
            )
    }

    @Test
    @DisplayName(value = "댓글을 삭제하는 API를 호출합니다.")
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
            .andExpect(jsonPath("$.data").value("Deleted comment successfully"))
            .andDo(
                document(
                    "comment-delete",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("commentId").type(JsonFieldType.STRING).description("댓글 아이디"),
                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("작성자 아이디")
                    ),
                    responseFields(
                        fieldWithPath("data").type(JsonFieldType.STRING).description("성공 여부 메시지")
                    )
                )
            )
    }

    @Test
    @DisplayName(value = "게시글에 해당하는 댓글들을 조회하는 API를 호출합니다.")
    fun findCommentByPostIdApi() {
        // given
        val postId = "postId"
        val offset = 0
        val limit = 10
        val listOf = listOf(BoardComment("article", "username", "memberId"))

        given(findComment.findCommentByPostId(postId, offset, limit)).willReturn(listOf)

        // when  // then
        mockMvc
            .perform(
                get("/api/v1/comments")
                    .queryParam("postId", postId)
                    .queryParam("offset", offset.toString())
                    .queryParam("limit", limit.toString())
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data[0].article").isString)
            .andExpect(jsonPath("$.data[0].username").isString)
            .andExpect(jsonPath("$.data[0].memberId").isString)
            .andDo(
                document(
                    "comment-board-get",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    queryParameters(
                        parameterWithName("postId").description("게시글 아이디"),
                        parameterWithName("offset").description("Page Offset"),
                        parameterWithName("limit").description("컨텐츠 개수")
                    ),
                    responseFields(
                        fieldWithPath("data[].article").type(JsonFieldType.STRING).description("댓글 내용"),
                        fieldWithPath("data[].username").type(JsonFieldType.STRING).description("작성자 이름"),
                        fieldWithPath("data[].memberId").type(JsonFieldType.STRING).description("작성자 아이디")
                    )
                )
            )
    }

    private fun createCommentRequest() = CreateCommentRequest("article", "postId", "memberId")

    private fun deleteCommentRequest() = DeleteCommentRequest("commentId", "memberId")

    private fun createCommentDto() =
        Comment(1L, "commentId", "article", "postId", "memberId", LocalDateTime.now(), LocalDateTime.now())
}
