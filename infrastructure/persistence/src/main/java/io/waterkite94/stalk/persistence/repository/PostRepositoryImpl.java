package io.waterkite94.stalk.persistence.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import io.waterkite94.stalk.persistence.dto.BoardPostDto;
import io.waterkite94.stalk.persistence.enrity.QCommentEntity;
import io.waterkite94.stalk.persistence.enrity.QPostEntity;
import io.waterkite94.stalk.persistence.enrity.QPostLikeEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

	@Autowired
	private final JPAQueryFactory queryFactory;

	@Override
	public List<BoardPostDto> findBoardPosts(Integer offset, Integer limit) {
		return queryFactory.select(Projections.constructor(BoardPostDto.class,
				QPostEntity.postEntity.title,
				QPostEntity.postEntity.article,
				QPostLikeEntity.postLikeEntity.count().intValue(),
				QCommentEntity.commentEntity.count().intValue(),
				QPostEntity.postEntity.memberId
			)).from(QPostEntity.postEntity)
			.leftJoin(QPostLikeEntity.postLikeEntity)
			.on(QPostLikeEntity.postLikeEntity.postId.eq(QPostEntity.postEntity.postId))
			.leftJoin(QCommentEntity.commentEntity)
			.on(QCommentEntity.commentEntity.postId.eq(QCommentEntity.commentEntity.postId))
			.groupBy(QPostEntity.postEntity.title, QPostEntity.postEntity.article, QPostEntity.postEntity.memberId)
			.offset(offset)
			.limit(limit)
			.fetch();
	}
}
