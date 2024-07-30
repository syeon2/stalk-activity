package io.waterkite94.stalk.persistence.enrity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
	name = "comment_like",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "MEMBER_ID_AND_COMMENT_ID",
			columnNames = {"member_id", "comment_id"})
	}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint", nullable = false)
	private Long id;

	@Column(name = "member_id", columnDefinition = "varchar(60)", nullable = false)
	private String memberId;

	@Column(name = "comment_id", columnDefinition = "varchar(60)", nullable = false)
	private String commentId;

	@Builder
	private CommentLikeEntity(String memberId, String commentId) {
		this.memberId = memberId;
		this.commentId = commentId;
	}
}
