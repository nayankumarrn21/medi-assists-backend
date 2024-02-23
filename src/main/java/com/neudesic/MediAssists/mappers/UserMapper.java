package com.neudesic.MediAssists.mappers;


import com.neudesic.MediAssists.dto.UserDto;
import com.neudesic.MediAssists.modules.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "id", target = "id")
    UserDto userToUserDTO(User user);

    @Mapping(source = "id", target = "id")
    User userDTOToUser(UserDto userDTO);
}
