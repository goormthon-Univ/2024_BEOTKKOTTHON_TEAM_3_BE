package com.univ.haksamo.domain.group.service;

import com.univ.haksamo.domain.bookmark.entity.UserGroup;
import com.univ.haksamo.domain.bookmark.repository.UserGroupRespository;
import com.univ.haksamo.domain.group.controller.res.FavoriteGroup;
import com.univ.haksamo.domain.group.entity.Group;
import com.univ.haksamo.domain.group.repository.GroupJpaRepository;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupWriteService {
    private final UserGroupRespository userGroupRespository;
    private final UserRepository userRepository;
    private final GroupJpaRepository groupJpaRepository;

    public void saveFavoriteGroups(List<FavoriteGroup> favoriteGroups){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());

        for(FavoriteGroup favoriteGroup : favoriteGroups){
            Group group = groupJpaRepository.findAllByName(favoriteGroup.getName());
            UserGroup userGroup = UserGroup.builder()
                    .user(user)
                    .group(group)
                    .build();

            userGroupRespository.save(userGroup);
        }
    }

}
