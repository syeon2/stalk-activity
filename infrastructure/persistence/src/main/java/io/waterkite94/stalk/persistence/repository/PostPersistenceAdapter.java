package io.waterkite94.stalk.persistence.repository;

import java.util.Objects;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.application.port.PostPersistencePort;
import io.waterkite94.stalk.domain.model.vo.Post;
import io.waterkite94.stalk.persistence.enrity.PostEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostPersistenceAdapter implements PostPersistencePort {

	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@NotNull
	@Override
	public Post save(@NotNull Post post) {
		PostEntity savedPost = postRepository.save(postMapper.toEntity(post));

		return postMapper.toDomain(savedPost);
	}

	@NotNull
	@Override
	public Post findPostByPostId(@NotNull String postId) {
		Optional<PostEntity> findPostOptional = postRepository.findByPostId(postId);

		return Objects.requireNonNull(findPostOptional.map(postMapper::toDomain).orElse(null));

	}

	@Transactional
	@Override
	public void deleteByPostId(@NotNull String postId) {
		postRepository.deleteByPostId(postId);
	}
}
