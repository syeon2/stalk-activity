package io.waterkite94.stalk.persistence.adapter;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import io.waterkite94.stalk.application.port.LikePersistencePort;
import io.waterkite94.stalk.domain.model.vo.CommentLike;
import io.waterkite94.stalk.domain.model.vo.PostLike;
import io.waterkite94.stalk.persistence.repository.CommentLikeRepository;
import io.waterkite94.stalk.persistence.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LikePersistenceAdapter implements LikePersistencePort {

	private final PostLikeRepository postLikeRepository;
	private final CommentLikeRepository commentLikeRepository;
	private final LikeMapper likeMapper;

	@Override
	public void savePostLike(@NotNull PostLike postLike) {
		postLikeRepository.save(likeMapper.toPostLikeEntity(postLike));
	}

	@Override
	public void deletePostLike(@NotNull String memberId, @NotNull String postId) {
		postLikeRepository.deleteByMemberIdAndPostId(memberId, postId);
	}

	@Override
	public void saveCommentLike(@NotNull CommentLike commentLike) {
		commentLikeRepository.save(likeMapper.toCommentLikeEntity(commentLike));
	}

	@Override
	public void deleteCommentLike(@NotNull String memberId, @NotNull String commentId) {
		commentLikeRepository.deleteByMemberIdAndCommentId(memberId, commentId);
	}
}
