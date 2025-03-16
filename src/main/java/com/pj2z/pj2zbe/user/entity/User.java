package com.pj2z.pj2zbe.user.entity;

import com.pj2z.pj2zbe.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    private String password;
    private String nickname;

    @Column(nullable = false,name = "GOAL_YN")
    @Enumerated(EnumType.STRING)
    private UserGoalYN userGoalYN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private int baseTickets;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.ROLE_USER;
        this.userGoalYN = UserGoalYN.N;
        this.baseTickets = 5;
    }

    public void useBaseTickets() {
        this.baseTickets = baseTickets - 1;
    }

    public void setBaseTicket(int baseTicket) {
        this.baseTickets = baseTicket;
    }
}
