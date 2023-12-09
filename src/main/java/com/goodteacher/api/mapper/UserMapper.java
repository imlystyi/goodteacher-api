// TODO: Delete this file

//package com.goodteacher.api.mapper;
//
//import com.goodteacher.api.dto.UserDto;
//import com.goodteacher.api.dto.UserSaveDto;
//import com.goodteacher.api.entity.User;
//
//public class UserMapper {
//    public static UserDto fromEntityToDto(final User entity) {
//        return entity == null
//               ? null
//               : UserDto.builder()
//                        .id(entity.getId())
//                        .nickname(entity.getNickname())
//                        .firstName(entity.getFirstName())
//                        .lastName(entity.getLastName())
//                        .patronymic(entity.getPatronymic())
//                        .birthDate(entity.getBirthDate())
//                        .build();
//    }
//
//    public static User fromSaveDtoToEntity(final UserSaveDto saveDto) {
//        return saveDto == null
//               ? null
//               : User.builder()
//                     .nickname(saveDto.getNickname())
//                     .email(saveDto.getEmail())
//                     .password(saveDto.getPassword())
//                     .firstName(saveDto.getFirstName())
//                     .lastName(saveDto.getLastName())
//                     .patronymic(saveDto.getPatronymic())
//                     .birthDate(saveDto.getBirthDate())
//                     .build();
//    }
//}
