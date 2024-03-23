package com.univ.haksamo.domain.user.dto;


import com.univ.haksamo.domain.user.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Builder
@Setter
@ToString
public class UserDto {

    @NotBlank(message = "학교를 선택해주세요")
    private String univName;
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @Email
    private String email;

    @NotBlank(message = "학과를 입력해주세요")
    private String major;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    public UserDto( String univName, String name, String email, String major, String password) {
        this.univName = univName;
        this.name = name;
        this.email = email;
        this.major = major;
        this.password = password;
    }
}
