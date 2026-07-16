package com.back.domain.member.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import com.back.global.jpa.entity.BaseEntity

//회원 정보를 저장하는 JPA 엔티티
@Entity
class Member() : BaseEntity() {

    //중복될 수 없는 사용자 아이디
    @field:Column(unique = true)
    lateinit var username: String

    //사용자 비밀번호
    lateinit var password: String

    //사용자 닉네임
    lateinit var nickname: String

    //회원 정보를 받아 객체를 생성하는 생성자
    constructor(
        username: String,
        password: String,
        nickname: String
    ) : this() {
        this.username = username
        this.password = password
        this.nickname = nickname
    }
}