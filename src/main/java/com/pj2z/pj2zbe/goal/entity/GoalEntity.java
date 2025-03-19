package com.pj2z.pj2zbe.goal.entity;

import com.pj2z.pj2zbe.goal.entity.enums.GoalUsedYN;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity(name = "GOALS")
@Getter
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String goalName;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "used_yn")
    @ColumnDefault("'Y'") // Hibernate가 테이블 생성 시 기본값 설정
    private GoalUsedYN usedYN = GoalUsedYN.Y;

    /*
       INSERT 전에 usedYN 값이 null이면 기본값 Y로 설정
    */
    @PrePersist
    public void setDefaultValues() {
        if (usedYN == null) {
            usedYN = GoalUsedYN.Y;
        }
    }

}
