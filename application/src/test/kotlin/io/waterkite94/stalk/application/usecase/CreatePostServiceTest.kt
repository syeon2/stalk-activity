package io.waterkite94.stalk.application.usecase

import io.waterkite94.stalk.application.IntegrationTestSupport
import io.waterkite94.stalk.domain.model.vo.Post
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify

class CreatePostServiceTest : IntegrationTestSupport() {
    @InjectMocks
    lateinit var createPostService: CreatePostService

    @Test
    @DisplayName(value = "게시글의 PostId를 생성하고 Repository에 전달합니다.")
    fun createPost() {
        // given
        val post = createPostDto()

        given(postPersistencePort.save(any())).willAnswer { invocation ->
            invocation.arguments[0] as Post
        }

        // when
        createPostService.createPost(post)

        // then
        val postCaptor = argumentCaptor<Post>()
        verify(postPersistencePort).save(postCaptor.capture())

        val capturedPost = postCaptor.firstValue

        assertThat(capturedPost)
            .extracting("title", "article", "memberId")
            .containsExactlyInAnyOrder(post.title, post.article, post.memberId)
    }

    private fun createPostDto() = Post(title = "제목입니다.", article = "게시글입니다.", memberId = "회원아이디입니다.")
}
