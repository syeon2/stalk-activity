package io.waterkite94.stalk.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.waterkite94.stalk.persistence.enrity.CommentLikeEntity;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Long> {

	void deleteByMemberIdAndCommentId(String memberId, String commentId);
}
