package com.univ.haksamo.domain.user.dto;


import com.univ.haksamo.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Builder
@Setter
public class UserDto {
    private Long id;
    private String univName;

    private String name;
    private String email;
    private String major;

    private String password;

    public UserDto(Long id, String univName, String name, String email, String major, String password) {
        this.id = id;
        this.univName = univName;
        this.name = name;
        this.email = email;
        this.major = major;
        this.password = password;
    }

    public static UserDto toDTO(User entity, String univName) {
        return UserDto.builder()
                .id(entity.getId())
                .major(entity.getMajor())
                .email(entity.getEmail())
                .name(entity.getName())
                .password(entity.getPassword())
                .univName(univName)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
