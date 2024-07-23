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
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint")
	private long id;

	@Column(name = "post_id", columnDefinition = "varchar(60)", nullable = false, unique = true)
	private String postId;

	@Column(name = "title", columnDefinition = "varchar(60)", nullable = false)
	private String title;

	@Column(name = "article", columnDefinition = "varchar(255)", nullable = false)
	private String article;

	@Column(name = "member_id", columnDefinition = "varchar(60)", nullable = false)
	private String memberId;

	@Builder
	private PostEntity(long id, String postId, String title, String article, String memberId) {
		this.postId = postId;
		this.title = title;
		this.article = article;
		this.memberId = memberId;
	}
}
