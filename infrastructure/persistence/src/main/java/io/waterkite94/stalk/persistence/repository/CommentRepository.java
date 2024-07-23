package io.waterkite94.stalk.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.waterkite94.stalk.persistence.enrity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	void deleteByCommentId(String commentId);
}
