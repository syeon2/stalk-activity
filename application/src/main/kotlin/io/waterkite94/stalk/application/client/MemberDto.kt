package io.waterkite94.stalk.application.client

import java.time.LocalDateTime

data class MemberDto(
    val id: Long? = null,
    var memberId: String? = null,
    val username: String,
    val email: String,
    var password: String,
    val phoneNumber: String,
    val introduction: String,
    val profileImageUrl: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)
