package com.back.global.jpa.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

//여러 엔티티가 공통으로 상속받는 부모 클래스
@MappedSuperclass

//생성일과 수정일을 자동으로 기록하는 리스너 등록
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    //엔티티의 기본 키
    @Id

    //데이터베이스에서 ID를 자동으로 증가
    @GeneratedValue(strategy = IDENTITY)
    val id: Int = 0

    //엔티티가 처음 저장된 날짜와 시간
    @CreatedDate
    lateinit var createDate: LocalDateTime

    //엔티티가 마지막으로 수정된 날짜와 시간
    @LastModifiedDate
    lateinit var modifyDate: LocalDateTime
}