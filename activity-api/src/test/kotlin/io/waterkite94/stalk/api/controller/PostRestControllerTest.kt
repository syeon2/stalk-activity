package io.waterkite94.stalk.api.controller

import io.waterkite94.stalk.api.ControllerTestSupport
import io.waterkite94.stalk.api.dto.request.CreatePostRequest
import io.waterkite94.stalk.application.usecase.CreatePost
import io.waterkite94.stalk.domain.model.vo.Post
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(controllers = [PostRestController::class])
class PostRestControllerTest : ControllerTestSupport() {
    @MockBean
    private lateinit var createPost: CreatePost

    @Autowired
    private lateinit var postRestController: PostRestController

    @Test
    @DisplayName(value = "게시글을 생성하는 API를 호출합니다.")
    fun createPostApi() {
        // given
        val request = requestDto()
        val result = postDto()

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
    }

    private fun requestDto() = CreatePostRequest("title", "article", "memberId")

    private fun postDto() = Post(1L, "postId", "title", "article", LocalDateTime.now(), LocalDateTime.now(), "memberId")
}
