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
	name = "post_like",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "POST_ID_AND_MEMBER_ID",
			columnNames = {"member_id", "post_id"})
	}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint", nullable = false)
	private Long id;

	@Column(name = "member_id", columnDefinition = "varchar(60)", nullable = false)
	private String memberId;

	@Column(name = "post_id", columnDefinition = "varchar(60)", nullable = false)
	private String postId;

	@Builder
	private PostLikeEntity(String memberId, String postId) {
		this.memberId = memberId;
		this.postId = postId;
	}
}
