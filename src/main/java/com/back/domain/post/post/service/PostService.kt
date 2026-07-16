package com.back.domain.post.post.service

import com.back.domain.member.member.entity.Member
import com.back.domain.post.post.entity.Post
import com.back.domain.post.post.repository.PostRepository
import org.springframework.stereotype.Service
import java.util.Optional

//게시글 관련 비즈니스 로직을 처리하는 서비스
@Service
class PostService(
    //게시글 데이터를 저장하고 조회하는 저장소
    private val postRepository: PostRepository
) {

    //저장된 전체 게시글 수를 반환
    fun count(): Long {
        return postRepository.count()
    }

    //ID에 해당하는 게시글을 조회
    fun findById(id: Long): Optional<Post> {
        return postRepository.findById(id)
    }

    //게시글의 제목과 내용을 수정
    fun modify(post: Post, title: String, content: String) {
        post.title = title
        post.content = content
    }

    //작성자, 제목, 내용으로 게시글을 생성하고 저장
    fun write(
        author: Member,
        title: String,
        content: String
    ): Post {
        val post = Post(author, title, content)

        postRepository.save(post)

        return post
    }
}