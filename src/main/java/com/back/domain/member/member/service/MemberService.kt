package com.back.domain.member.member.service

import com.back.domain.member.member.entity.Member
import com.back.domain.member.member.repository.MemberRepository
import org.springframework.stereotype.Service

//회원 관련 비즈니스 로직을 처리하는 서비스
@Service
class MemberService(
    //회원 데이터를 저장하고 조회하는 저장소
    private val memberRepository: MemberRepository
) {

    //저장된 전체 회원 수를 반환
    fun count(): Long {
        return memberRepository.count()
    }

    //전달받은 정보로 회원을 생성하고 저장
    fun join(
        username: String,
        password: String,
        nickname: String
    ): Member {
        return memberRepository.save(
            Member(username, password, nickname)
        )
    }
}