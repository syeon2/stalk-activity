package io.waterkite94.stalk.application.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "member-service")
interface MemberServiceClient {
    @GetMapping("/internal/members/{memberId}")
    fun getMember(
        @PathVariable("memberId") memberId: String
    ): MemberDto
}
