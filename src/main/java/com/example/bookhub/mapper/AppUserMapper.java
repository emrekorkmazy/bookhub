package com.example.bookhub.mapper;

import com.example.bookhub.dto.AppUserDTO;
import com.example.bookhub.entity.AppUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AppUserMapper {
    AppUser toEntity(AppUserDTO dto);
    AppUserDTO toDto(AppUser user);
    List<AppUserDTO> toDtoList(List<AppUser> users);
    List<AppUser> toEntityList(List<AppUserDTO> dtos);
}
