package io.waterkite94.stalk.persistence.adapter;

import org.springframework.stereotype.Component;

import io.waterkite94.stalk.domain.model.vo.Post;
import io.waterkite94.stalk.persistence.enrity.PostEntity;

@Component
public class PostMapper {

	public PostEntity toEntity(Post post) {
		return PostEntity.builder()
			.postId(post.getPostId())
			.title(post.getTitle())
			.article(post.getArticle())
			.memberId(post.getMemberId())
			.stockId(post.getStockId())
			.build();
	}

	public Post toDomain(PostEntity postEntity) {
		return new Post(
			postEntity.getId(),
			postEntity.getPostId(),
			postEntity.getTitle(),
			postEntity.getArticle(),
			postEntity.getCreatedAt(),
			postEntity.getUpdatedAt(),
			postEntity.getMemberId(),
			postEntity.getStockId()
		);
	}
}
