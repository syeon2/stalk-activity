package io.waterkite94.stalk.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.waterkite94.stalk.persistence.enrity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

	Optional<PostEntity> findByPostId(String postId);

	void deleteByPostId(String postId);
}
