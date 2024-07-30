package io.waterkite94.stalk.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.waterkite94.stalk.persistence.enrity.PostLikeEntity;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {

	void deleteByMemberIdAndPostId(String memberId, String postId);
}
