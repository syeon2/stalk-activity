package io.waterkite94.stalk.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class BoardPostDto {

	private String title;
	private String article;
	private Integer postLikeCount;
	private Integer commentCount;
	private String memberId;

	@QueryProjection
	public BoardPostDto(String title, String article, Integer postLikeCount, Integer commentCount, String memberId) {
		this.title = title;
		this.article = article;
		this.postLikeCount = postLikeCount;
		this.commentCount = commentCount;
		this.memberId = memberId;
	}
}
