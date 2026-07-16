package com.back.domain.post.post.entity

import com.back.domain.member.member.entity.Member
import com.back.global.jpa.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

//게시글 정보를 저장하는 JPA 엔티티
@Entity
class Post() : BaseEntity() {

    //여러 게시글이 하나의 회원을 작성자로 가질 수 있는 N:1 관계
    @ManyToOne
    lateinit var author: Member

    //게시글 제목
    lateinit var title: String

    //긴 게시글 내용을 TEXT 타입으로 저장
    @Column(columnDefinition = "TEXT")
    lateinit var content: String

    //작성자, 제목, 내용을 받아 게시글 객체를 생성하는 생성자
    constructor(
        author: Member,
        title: String,
        content: String
    ) : this() {
        this.author = author
        this.title = title
        this.content = content
    }
}