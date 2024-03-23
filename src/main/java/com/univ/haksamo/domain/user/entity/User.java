package com.univ.haksamo.domain.user.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.univ.haksamo.domain.university.entity.University;
import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity implements UserDetails {
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
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String password;

    public static User toEntity(UserDto dto, University university) {
        return User.builder()
                .major(dto.getMajor())
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .university(university)
                .role(Role.ROLE_USER)
                .build();
    }

    public void setUserFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
