package io.waterkite94.stalk.persistence.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.domain.model.vo.Comment;
import io.waterkite94.stalk.persistence.enrity.CommentEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentPersistenceAdapter {

	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;

	public Comment save(Comment comment) {
		CommentEntity savedComment = commentRepository.save(commentMapper.toEntity(comment));

		return commentMapper.toDomain(savedComment);
	}

	@Transactional
	public void deleteByCommentId(String commentId) {
		commentRepository.deleteByCommentId(commentId);
	}
}
