package com.back.global.initData

import com.back.domain.member.member.entity.Member
import com.back.domain.member.member.service.MemberService
import com.back.domain.post.post.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.transaction.annotation.Transactional

//애플리케이션 실행 시 기본 데이터를 생성하는 설정 클래스
@Configuration
class BaseInitData(
    //회원 관련 기능을 사용하는 서비스
    private val memberService: MemberService,

    //게시글 관련 기능을 사용하는 서비스
    private val postService: PostService
) {

    //트랜잭션 프록시를 거쳐 work1()을 호출하기 위한 자기 자신 주입
    @Autowired
    @Lazy
    private lateinit var self: BaseInitData

    //애플리케이션 실행 직후 기본 데이터 초기화 작업을 실행
    @Bean
    fun baseInitDataApplicationRunner(): ApplicationRunner {
        return ApplicationRunner {
            self.work1()
        }
    }

    //기본 회원과 게시글 데이터를 하나의 트랜잭션으로 생성
    @Transactional
    fun work1() {
        //회원 데이터가 이미 있으면 초기화를 종료
        if (memberService.count() > 0) return

        //기본 회원 데이터 생성
        memberService.join("system", "1234", "시스템")
        memberService.join("admin", "1234", "관리자")

        //게시글 작성자로 사용할 회원 생성
        val memberUser1: Member =
            memberService.join("user1", "1234", "유저1")

        val memberUser2: Member =
            memberService.join("user2", "1234", "유저2")

        memberService.join("user3", "1234", "유저3")

        //게시글 데이터가 이미 있으면 게시글 생성을 종료
        if (postService.count() > 0) return

        //기본 게시글 데이터 생성
        postService.write(memberUser1, "제목 1", "내용 1")
        postService.write(memberUser2, "제목 2", "내용 2")

        //초기화 완료 메시지 출력
        println("기본 데이터가 초기화되었습니다.")
    }
}