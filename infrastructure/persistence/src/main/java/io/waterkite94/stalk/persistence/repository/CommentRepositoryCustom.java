package io.waterkite94.stalk.persistence.repository;

import java.util.List;

import io.waterkite94.stalk.persistence.enrity.CommentEntity;

public interface CommentRepositoryCustom {

	List<CommentEntity> findCommentsByPostId(String postId, Integer offset, Integer limit);
}
