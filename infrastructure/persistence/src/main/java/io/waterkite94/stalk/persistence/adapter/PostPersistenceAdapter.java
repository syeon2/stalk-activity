package io.waterkite94.stalk.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.waterkite94.stalk.application.port.PostPersistencePort;
import io.waterkite94.stalk.domain.model.vo.BoardPost;
import io.waterkite94.stalk.domain.model.vo.Post;
import io.waterkite94.stalk.persistence.dto.BoardPostDto;
import io.waterkite94.stalk.persistence.enrity.PostEntity;
import io.waterkite94.stalk.persistence.repository.PostRepository;
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

	@Nullable
	@Override
	public Post findPostByPostId(@NotNull String postId) {
		Optional<PostEntity> findPostOptional = postRepository.findByPostId(postId);

		return findPostOptional.map(postMapper::toDomain).orElse(null);
	}

	@Override
	@Transactional
	public void deleteByPostId(@NotNull String postId) {
		postRepository.deleteByPostId(postId);
	}

	@NotNull
	@Override
	public List<BoardPost> findBoardPosts(@NotNull String stockId, int offset, int limit) {
		return postRepository.findBoardPosts(stockId, offset, limit).stream()
			.map(this::mapper)
			.collect(Collectors.toList());
	}

	private BoardPost mapper(BoardPostDto dto) {
		return new BoardPost(
			dto.getTitle(),
			dto.getArticle(),
			null,
			dto.getPostLikeCount(),
			dto.getCommentCount(),
			null,
			dto.getMemberId()
		);
	}
}
