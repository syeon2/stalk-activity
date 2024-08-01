package io.waterkite94.stalk.persistence.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.domain.model.vo.Comment;
import io.waterkite94.stalk.persistence.IntegrationTestSupport;
import io.waterkite94.stalk.persistence.adapter.CommentPersistenceAdapter;
import io.waterkite94.stalk.persistence.enrity.CommentEntity;

class CommentPersistenceAdapterTest extends IntegrationTestSupport {

	@Autowired
	private CommentPersistenceAdapter commentPersistenceAdapter;

	@Autowired
	private CommentRepository commentRepository;

	@BeforeEach
	void before() {
		commentRepository.deleteAllInBatch();
	}

	@Test
	@Transactional
	@DisplayName(value = "댓글을 저장합니다.")
	void save() {
		// given
		String commentId = "commentId";
		String article = "article";
		String postId = "post_id";
		String memberId = "member_id";

		Comment comment = createCommentDto(commentId, article, postId, memberId);

		// when
		commentPersistenceAdapter.save(comment);

		// then
		List<CommentEntity> findComments = commentRepository.findAll();

		assertThat(findComments.size()).isEqualTo(1);
		assertThat(findComments.get(0))
			.extracting("commentId", "article", "postId", "memberId")
			.containsExactly(commentId, article, postId, memberId);
	}

	@Test
	@Transactional
	@DisplayName(value = "댓글 아이디를 사용하여 댓글을 조회합니다.")
	void findByCommentId() {
		// given
		String commentId = "commentId";
		String article = "article";
		String postId = "post_id";
		String memberId = "member_id";
		Comment comment = createCommentDto(commentId, article, postId, memberId);
		commentPersistenceAdapter.save(comment);

		assertThat(commentRepository.findAll().size()).isEqualTo(1);

		// when
		Comment findComment = commentPersistenceAdapter.findByCommentId(commentId);

		// then
		assertThat(findComment)
			.extracting("commentId", "article", "postId", "memberId")
			.containsExactly(commentId, article, postId, memberId);
	}

	@Test
	@Transactional
	@DisplayName(value = "댓글을 삭제합니다.")
	void delete() {
		// given
		String commentId = "commentId";
		String article = "article";
		String postId = "post_id";
		String memberId = "member_id";
		Comment comment = createCommentDto(commentId, article, postId, memberId);

		Comment savedComment = commentPersistenceAdapter.save(comment);
		assertThat(commentRepository.findAll().size()).isEqualTo(1);

		// when
		commentPersistenceAdapter.deleteByCommentId(commentId);

		// then
		assertThat(commentRepository.findAll().size()).isEqualTo(0);
	}

	private @NotNull Comment createCommentDto(String commentId, String article, String psotId, String memberId) {
		return new Comment(null, commentId, article, psotId, memberId, null, null);
	}
}
