package io.waterkite94.stalk.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.persistence.enrity.CommentLikeEntity;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	void deleteByMemberIdAndCommentId(String memberId, String commentId);
}
