package com.tracklist.tracker.mapper;

import com.tracklist.tracker.dto.UserSignUpDTO;
import com.tracklist.tracker.dto.UsersDTO;
import com.tracklist.tracker.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UsersDTO usersToUsersDTO(Users users);

//    @Mapping(source = "user" , target = "user")
    Users mapUserSignUpDTOTOUsersEntity(UserSignUpDTO userSignUpDTO);

    UserSignUpDTO mapUsersEntityToUserSignUpDTO(Users users);

    Users mapUsersDTOToUsersEntity(UsersDTO usersDTO);

}
