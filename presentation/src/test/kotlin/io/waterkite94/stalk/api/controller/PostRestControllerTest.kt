package io.waterkite94.stalk.api.controller

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import io.waterkite94.stalk.api.ControllerTestSupport
import io.waterkite94.stalk.api.dto.request.CreatePostRequest
import io.waterkite94.stalk.api.dto.request.DeletePostRequest
import io.waterkite94.stalk.application.usecase.CreatePost
import io.waterkite94.stalk.application.usecase.DeletePost
import io.waterkite94.stalk.domain.model.vo.Post
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(controllers = [PostRestController::class])
class PostRestControllerTest : ControllerTestSupport() {
    @MockBean
    private lateinit var createPost: CreatePost

    @MockBean
    private lateinit var deletePost: DeletePost

    @Test
    @DisplayName(value = "게시글을 생성하는 API를 호출합니다.")
    fun createPostApi() {
        // given
        val request = createPostRequest()
        val result = createPostDto()

        given(createPost.createPost(request.toDomain()))
            .willReturn(result)

        // when   // then
        mockMvc
            .perform(
                post("/api/v1/posts")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.postId").isString)
            .andExpect(jsonPath("$.data.title").isString)
            .andExpect(jsonPath("$.data.article").isString)
            .andExpect(jsonPath("$.data.memberId").isString)
            .andDo(
                document(
                    "post-create",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                        fieldWithPath("article").type(JsonFieldType.STRING).description("게시글 내용"),
                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("작성자 아이디")
                    ),
                    responseFields(
                        fieldWithPath("data.postId").type(JsonFieldType.STRING).description("게시글 아이디"),
                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("게시글 제목"),
                        fieldWithPath("data.article").type(JsonFieldType.STRING).description("게시글 내용"),
                        fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("작성자 아이디")
                    )
                )
            )

        verify(createPost, times(1)).createPost(request.toDomain())
    }

    @Test
    @DisplayName(value = "게시글을 삭제하는 API를 호출합니다.")
    fun deletePostApi() {
        // given
        val deletePostRequest = deletePostRequest()
        doNothing().`when`(deletePost).deletePost(deletePostRequest.postId, deletePostRequest.memberId)

        // when  // then
        mockMvc
            .perform(
                delete("/api/v1/posts")
                    .content(objectMapper.writeValueAsString(deletePostRequest))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").value("Deleted post successfully"))
            .andDo(
                document(
                    "post-delete",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("postId").type(JsonFieldType.STRING).description("게시글 아이디"),
                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("회원 아이디")
                    ),
                    responseFields(
                        fieldWithPath("data").type(JsonFieldType.STRING).description("요청 성공 메시지")
                    )
                )
            )

        verify(deletePost, times(1)).deletePost(deletePostRequest.postId, deletePostRequest.memberId)
    }

    private fun deletePostRequest() = DeletePostRequest("postId", "memberId")

    private fun createPostRequest() = CreatePostRequest("title", "article", "memberId")

    private fun createPostDto() =
        Post(1L, "postId", "title", "article", LocalDateTime.now(), LocalDateTime.now(), "memberId")
}
