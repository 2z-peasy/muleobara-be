package com.pj2z.pj2zbe.mbti.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj2z.pj2zbe.auth.entity.User;
import com.pj2z.pj2zbe.common.entity.BaseTimeEntity;
import com.pj2z.pj2zbe.mbti.core.MbtiPairSum;
import com.pj2z.pj2zbe.mbti.entity.enums.MbtiType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@MbtiPairSum
@Builder
@AllArgsConstructor
@Table(name = "mbtis")
public class Mbti extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "mbti_type")
    @Enumerated(EnumType.STRING)
    private MbtiType mbtiType;
    @Max(100) @Min(0)
    @Column(nullable = false, name = "e_percent")
    private Long ePercent;
    @Max(100) @Min(0)
    @Column(nullable = false, name = "i_percent")
    private Long iPercent;

    @Max(100) @Min(0)
    @Column(nullable = false, name = "n_percent")
    private Long nPercent;
    @Max(100) @Min(0)
    @Column(nullable = false, name = "s_percent")
    private Long sPercent;


    @Max(100) @Min(0)
    @Column(nullable = false, name = "t_percent")
    private Long tPercent;

    @Max(100) @Min(0)
    @Column(nullable = false, name = "f_percent")
    private Long fPercent;


    @Max(100) @Min(0)
    @Column(nullable = false, name = "p_percent")
    private Long pPercent;
    @Max(100) @Min(0)
    @Column(nullable = false, name = "j_percent")
    private Long jPercent;


    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

}

