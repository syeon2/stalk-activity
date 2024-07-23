package io.waterkite94.stalk.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.waterkite94.stalk.persistence.enrity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	Optional<CommentEntity> findByCommentId(String commentId);

	void deleteByCommentId(String commentId);
}
