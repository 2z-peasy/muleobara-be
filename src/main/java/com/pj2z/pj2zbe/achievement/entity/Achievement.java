package com.pj2z.pj2zbe.achievement.entity;

import com.pj2z.pj2zbe.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity(name = "Achievements")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Achievement { //업적
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "like_count", nullable = false)
    @ColumnDefault("0")
    private Long likeCount= 0L;

    /*
       초기값 설정
     */
    @PrePersist
    public void setDefaultValues() {
        if (likeCount == null) {
            likeCount = 0L;
        }
    }

    /*
     엔티티 생성자
     */
    @Builder
    public Achievement(User user, Long likeCount) {
        this.user = user;
        this.likeCount = likeCount;
    }

    @Builder
    public Achievement(User user) {
        this.user = user;
        this.likeCount = 0L;
    }

    /*
     엔티티 메소드
     */
    public void addLikecount() {
        this.likeCount++;
    }

}
