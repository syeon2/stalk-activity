package io.waterkite94.stalk.persistence.adapter;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.application.port.CommentPersistencePort;
import io.waterkite94.stalk.domain.model.vo.Comment;
import io.waterkite94.stalk.persistence.enrity.CommentEntity;
import io.waterkite94.stalk.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements CommentPersistencePort {

	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;

	@NotNull
	@Override
	public Comment save(@NotNull Comment comment) {
		CommentEntity savedComment = commentRepository.save(commentMapper.toEntity(comment));

		return commentMapper.toDomain(savedComment);
	}

	@Nullable
	@Override
	public Comment findByCommentId(@NotNull String commentId) {
		Optional<CommentEntity> findCommentOptional = commentRepository.findByCommentId(commentId);

		return findCommentOptional.map(commentMapper::toDomain).orElse(null);
	}

	@Override
	@Transactional
	public void deleteByCommentId(@NotNull String commentId) {
		commentRepository.deleteByCommentId(commentId);
	}
}
