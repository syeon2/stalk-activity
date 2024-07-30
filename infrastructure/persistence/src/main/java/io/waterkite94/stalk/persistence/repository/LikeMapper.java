package io.waterkite94.stalk.persistence.repository;

import org.springframework.stereotype.Component;

import io.waterkite94.stalk.domain.model.vo.CommentLike;
import io.waterkite94.stalk.domain.model.vo.PostLike;
import io.waterkite94.stalk.persistence.enrity.CommentLikeEntity;
import io.waterkite94.stalk.persistence.enrity.PostLikeEntity;

@Component
public class LikeMapper {

	public PostLikeEntity toPostLikeEntity(PostLike domain) {
		return PostLikeEntity.builder()
			.memberId(domain.getMemberId())
			.postId(domain.getPostId())
			.build();
	}

	public PostLike toPostLikeDomain(PostLikeEntity entity) {
		return new PostLike(entity.getId(), entity.getMemberId(), entity.getPostId());
	}

	public CommentLikeEntity toCommentLikeEntity(CommentLike domain) {
		return CommentLikeEntity.builder()
			.memberId(domain.getMemberId())
			.commentId(domain.getCommentId())
			.build();
	}

	public CommentLike toCommentLikeDomain(CommentLikeEntity entity) {
		return new CommentLike(entity.getId(), entity.getMemberId(), entity.getCommentId());
	}

}
