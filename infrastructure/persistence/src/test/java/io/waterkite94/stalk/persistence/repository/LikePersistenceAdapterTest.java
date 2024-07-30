package io.waterkite94.stalk.persistence.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.domain.model.vo.CommentLike;
import io.waterkite94.stalk.domain.model.vo.PostLike;
import io.waterkite94.stalk.persistence.IntegrationTestSupport;
import io.waterkite94.stalk.persistence.enrity.CommentLikeEntity;
import io.waterkite94.stalk.persistence.enrity.PostLikeEntity;

class LikePersistenceAdapterTest extends IntegrationTestSupport {

	@Autowired
	private LikePersistenceAdapter likePersistenceAdapter;

	@Autowired
	private PostLikeRepository postLikeRepository;

	@Autowired
	private CommentLikeRepository commentLikeRepository;

	@BeforeEach
	void before() {
		postLikeRepository.deleteAllInBatch();
		commentLikeRepository.deleteAllInBatch();
	}

	@Test
	@Transactional
	@DisplayName(value = "게시글 좋아요 를 저장합니다.")
	void savePostLike() {
		// given
		PostLike postLike = new PostLike(null, "memberId", "postId");

		// when
		likePersistenceAdapter.savePostLike(postLike);

		// then
		List<PostLikeEntity> findPostLikes = postLikeRepository.findAll();

		assertThat(findPostLikes.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	@DisplayName(value = "게시글 좋아요 를 중복 저장할 수 없습니다.")
	void savePostLike_exception_unique() {
		// given
		PostLike postLike = new PostLike(null, "memberId", "postId");

		likePersistenceAdapter.savePostLike(postLike);

		// when // then
		assertThatThrownBy(() -> likePersistenceAdapter.savePostLike(postLike))
			.isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	@Transactional
	@DisplayName(value = "게시글 좋아요 를 삭제합니다.")
	void deletePostLike() {
		// given
		String memberId = "memberId";
		String postId = "postId";
		PostLike postLike = new PostLike(null, memberId, postId);
		likePersistenceAdapter.savePostLike(postLike);

		assertThat(postLikeRepository.findAll().size()).isEqualTo(1);

		// when
		likePersistenceAdapter.deletePostLike(memberId, postId);

		// then
		assertThat(postLikeRepository.findAll().size()).isEqualTo(0);
	}

	@Test
	@Transactional
	@DisplayName(value = "댓글 좋아요 를 저장합니다.")
	void saveCommentLike() {
		// given
		CommentLike commentLike = new CommentLike(null, "memberId", "commentId");

		// when
		likePersistenceAdapter.saveCommentLike(commentLike);

		// then
		List<CommentLikeEntity> findComments = commentLikeRepository.findAll();

		assertThat(findComments.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	@DisplayName(value = "댓글 좋아요 를 중복 저장할 수 없습니다.")
	void saveComment_exception_unique() {
		// given
		CommentLike commentLike = new CommentLike(null, "memberId", "commentId");

		likePersistenceAdapter.saveCommentLike(commentLike);

		// when // then
		assertThatThrownBy(() -> likePersistenceAdapter.saveCommentLike(commentLike))
			.isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	@Transactional
	@DisplayName(value = "댓글 좋아요 를 삭제합니다.")
	void deleteCommentLike() {
		// given
		String memberId = "memberId";
		String commentId = "commentId";
		CommentLike commentLike = new CommentLike(null, memberId, commentId);
		likePersistenceAdapter.saveCommentLike(commentLike);

		assertThat(commentLikeRepository.findAll().size()).isEqualTo(1);

		// when
		likePersistenceAdapter.deleteCommentLike(memberId, commentId);

		// then
		assertThat(commentLikeRepository.findAll().size()).isEqualTo(0);
	}
}
