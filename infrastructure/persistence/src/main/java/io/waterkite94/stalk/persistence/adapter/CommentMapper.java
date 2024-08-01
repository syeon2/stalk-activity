package io.waterkite94.stalk.persistence.adapter;

import org.springframework.stereotype.Component;

import io.waterkite94.stalk.domain.model.vo.Comment;
import io.waterkite94.stalk.persistence.enrity.CommentEntity;

@Component
public class CommentMapper {

	public CommentEntity toEntity(Comment comment) {
		return CommentEntity.builder()
			.commentId(comment.getCommentId())
			.article(comment.getArticle())
			.postId(comment.getPostId())
			.memberId(comment.getMemberId())
			.build();
	}

	public Comment toDomain(CommentEntity entity) {
		return new Comment(
			entity.getId(),
			entity.getCommentId(),
			entity.getArticle(),
			entity.getPostId(),
			entity.getMemberId(),
			entity.getCreatedAt(),
			entity.getUpdatedAt()
		);
	}
}
