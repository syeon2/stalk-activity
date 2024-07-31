package io.waterkite94.stalk.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.persistence.enrity.PostLikeEntity;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	void deleteByMemberIdAndPostId(String memberId, String postId);
}
