package com.univ.haksamo.domain.bookmark.service;

import com.univ.haksamo.domain.bookmark.entity.UserGroup;
import com.univ.haksamo.domain.bookmark.repository.UserGroupRespository;
import com.univ.haksamo.domain.bookmark.dto.FavoriteGroupDto;
import com.univ.haksamo.domain.group.entity.Group;
import com.univ.haksamo.domain.group.repository.GroupJpaRepository;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGroupService {
    private final UserGroupRespository userGroupRespository;
    private final UserRepository userRepository;
    private final GroupJpaRepository groupJpaRepository;

    public void saveFavoriteGroup(FavoriteGroupDto favoriteGroupDto){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).get();

        Group group = groupJpaRepository.findAllByName(favoriteGroupDto.getName());
        UserGroup userGroup = UserGroup.builder()
                .user(user)
                .group(group)
                .build();

        userGroupRespository.save(userGroup);
    }

    public void deleteFavoriteGroup(FavoriteGroupDto favoriteGroupDto){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).get();

        Group group = groupJpaRepository.findAllByName(favoriteGroupDto.getName());
        UserGroup userGroup = userGroupRespository.findByUserIdAndGroupId(user.getId(), group.getId());

        userGroupRespository.delete(userGroup);
    }


}
