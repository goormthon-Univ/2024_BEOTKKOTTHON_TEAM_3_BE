package com.univ.haksamo.domain.user.dto;

import com.univ.haksamo.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
@ToString
public class UserPageDto {
    private Long id;
    private String univName;

    private String name;
    private String email;
    private String major;

    public UserPageDto(Long id, String univName, String name, String email, String major) {
        this.id = id;
        this.univName = univName;
        this.name = name;
        this.email = email;
        this.major = major;
    }

    public static UserPageDto toDTO(User entity, String univName) {
        return UserPageDto.builder()
                .id(entity.getId())
                .major(entity.getMajor())
                .email(entity.getEmail())
                .name(entity.getName())
                .univName(univName)
                .build();
    }
}
