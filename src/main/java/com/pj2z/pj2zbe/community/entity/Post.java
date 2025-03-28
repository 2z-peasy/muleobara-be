package com.pj2z.pj2zbe.community.entity;

import com.pj2z.pj2zbe.common.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Long authorId;
    // 공감, 댓글, 투표 등 존재

    public Post(String title, String content, Long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
}
