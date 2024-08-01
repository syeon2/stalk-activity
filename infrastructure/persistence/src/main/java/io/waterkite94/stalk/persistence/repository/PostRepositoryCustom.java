package io.waterkite94.stalk.persistence.repository;

import java.util.List;

import io.waterkite94.stalk.persistence.dto.BoardPostDto;

public interface PostRepositoryCustom {

	List<BoardPostDto> findBoardPosts(Integer offset, Integer limit);
}
