package com.univ.haksamo.domain.user.entity;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.univ.haksamo.domain.university.entity.University;
import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "univId")
    private University university;

    private String name;
    private String fcmToken;
    private String email;

    private String major;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;

    public static User toEntity(UserDto dto, University university) {
        return User.builder()
                .major(dto.getMajor())
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .university(university)
                .build();
    }
}
