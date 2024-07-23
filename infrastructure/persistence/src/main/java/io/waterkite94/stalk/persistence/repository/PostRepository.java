package io.waterkite94.stalk.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.waterkite94.stalk.persistence.enrity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
