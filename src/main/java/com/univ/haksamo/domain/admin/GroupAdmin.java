package com.univ.haksamo.domain.admin;

import com.univ.haksamo.domain.user.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class GroupAdmin {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    private Long groupId; //반정규화 값으로 관리의 주의필요 -> 정합성 등등, 일대일 관계임, 해커톤때는 일단 이렇게 사용
}
