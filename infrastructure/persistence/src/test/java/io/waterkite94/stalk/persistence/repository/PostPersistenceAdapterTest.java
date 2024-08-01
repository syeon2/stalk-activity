package io.waterkite94.stalk.persistence.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.domain.model.vo.BoardPost;
import io.waterkite94.stalk.domain.model.vo.Post;
import io.waterkite94.stalk.persistence.IntegrationTestSupport;
import io.waterkite94.stalk.persistence.adapter.PostPersistenceAdapter;
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

	@Test
	@Transactional
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

	@Test
	void findPostByPostId() {
		// given
		Post savedPost = postPersistenceAdapter.save(createPost());
		assertThat(postRepository.findAll().size()).isEqualTo(1);

		// when
		Post findPost = postPersistenceAdapter.findPostByPostId(Objects.requireNonNull(savedPost.getPostId()));

		// then
		assertThat(findPost)
			.extracting("title", "article", "memberId")
			.containsExactlyInAnyOrder(savedPost.getTitle(), savedPost.getArticle(), savedPost.getMemberId());
	}

	@Test
	@Transactional
	@DisplayName(value = "게시글 아이디를 받아 게시글을 삭제합니다.")
	void deleteByPostId() {
		// given
		Post savedPost = postPersistenceAdapter.save(createPost());
		assertThat(postRepository.findAll().size()).isEqualTo(1);

		// when
		postPersistenceAdapter.deleteByPostId(Objects.requireNonNull(savedPost.getPostId()));

		// then
		List<PostEntity> findPosts = postRepository.findAll();

		assertThat(findPosts.size()).isEqualTo(0);
	}

	@Test
	@Transactional
	@DisplayName(value = "게시글을 조회합니다.")
	void findBoardPosts() {
		// given
		String stockId = "stockId";
		Integer offset = 0;
		Integer limit = 10;
		Post savedPost = postPersistenceAdapter.save(createPost());
		assertThat(postRepository.findAll().size()).isEqualTo(1);

		// when
		List<BoardPost> findBoardPosts = postPersistenceAdapter.findBoardPosts(stockId, offset, limit);

		// then
		assertThat(findBoardPosts.size()).isEqualTo(1);
		assertThat(findBoardPosts.get(0))
			.extracting("title", "article", "postLikeCount", "commentCount")
			.containsExactlyInAnyOrder(savedPost.getTitle(), savedPost.getArticle(), 0, 0);
	}

	public Post createPost() {
		return new Post(null, "postId", "title", "article", null, null, "memberId", "stockId");
	}
}
