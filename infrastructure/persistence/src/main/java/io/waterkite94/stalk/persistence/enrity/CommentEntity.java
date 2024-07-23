package io.waterkite94.stalk.persistence.enrity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint")
	private Long id;

	@Column(name = "comment_id", columnDefinition = "varchar(60)", nullable = false, unique = true)
	private String commentId;

	@Column(name = "article", columnDefinition = "varchar(255)", nullable = false)
	private String article;

	@Column(name = "post_id", columnDefinition = "varchar(60)", nullable = false)
	private String postId;

	@Column(name = "member_id", columnDefinition = "varchar(60)", nullable = false)
	private String memberId;

	@Builder
	private CommentEntity(String article, String commentId, String postId, String memberId) {
		this.article = article;
		this.commentId = commentId;
		this.postId = postId;
		this.memberId = memberId;
	}
}
