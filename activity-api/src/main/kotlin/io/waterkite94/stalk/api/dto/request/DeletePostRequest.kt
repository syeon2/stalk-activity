package io.waterkite94.stalk.api.dto.request

import jakarta.validation.constraints.NotBlank

data class DeletePostRequest(
    @field:NotBlank(message = "게시글 아이디는 빈칸을 허용하지 않습니다.")
    val postId: String,
    @field:NotBlank(message = "회원 아이디는 빈칸을 허용하지 않습니다.")
    val memberId: String
)
