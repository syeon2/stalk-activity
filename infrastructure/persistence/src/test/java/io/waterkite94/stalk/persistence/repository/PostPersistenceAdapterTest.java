package io.waterkite94.stalk.persistence.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.waterkite94.stalk.domain.model.vo.Post;
import io.waterkite94.stalk.persistence.IntegrationTestSupport;
import io.waterkite94.stalk.persistence.enrity.PostEntity;

class PostPersistenceAdapterTest extends IntegrationTestSupport {

	@Autowired
	private PostPersistenceAdapter postPersistenceAdapter;

	@Autowired
	private PostRepository postRepository;

	@BeforeEach
	void before() {
		postRepository.deleteAllInBatch();
	}

	@AfterEach
	void after() {
		postRepository.deleteAllInBatch();
	}

	@Test
	@DisplayName(value = "게시글을 저장합니다.")
	void save() {
		// given
		Post post = createPost();

		// when
		Post savedPost = postPersistenceAdapter.save(post);

		// then
		Optional<PostEntity> findPostOptional = postRepository.findById(Objects.requireNonNull(savedPost.getId()));

		assertThat(findPostOptional).isPresent().get()
			.extracting("title", "article", "memberId")
			.containsExactlyInAnyOrder(post.getTitle(), post.getArticle(), post.getMemberId());
	}

	public Post createPost() {
		return new Post(null, "postId", "title", "article", null, null, "memberId");
	}
}
