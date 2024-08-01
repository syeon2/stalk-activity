package io.waterkite94.stalk.persistence.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import io.waterkite94.stalk.persistence.enrity.CommentEntity;
import io.waterkite94.stalk.persistence.enrity.QCommentEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<CommentEntity> findCommentsByPostId(String postId, Integer offset, Integer limit) {
		return queryFactory
			.selectFrom(QCommentEntity.commentEntity)
			.where(QCommentEntity.commentEntity.postId.eq(postId))
			.offset(offset)
			.limit(limit)
			.fetch();
	}
}
