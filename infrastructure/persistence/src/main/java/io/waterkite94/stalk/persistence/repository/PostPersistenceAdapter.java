package io.waterkite94.stalk.persistence.repository;

import org.springframework.stereotype.Repository;

import io.waterkite94.stalk.domain.model.vo.Post;
import io.waterkite94.stalk.persistence.enrity.PostEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostPersistenceAdapter {

	private final PostRepository postRepository;
	private final PostMapper postMapper;

	public Post save(Post post) {
		PostEntity savedPost = postRepository.save(postMapper.toEntity(post));

		return postMapper.toDomain(savedPost);
	}
}
