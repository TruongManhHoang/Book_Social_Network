package com.devteria.identity.mapper;

import com.devteria.identity.dto.request.RoleUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devteria.identity.dto.request.RoleRequest;
import com.devteria.identity.dto.response.RoleResponse;
import com.devteria.identity.entity.Role;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "permissions", ignore = true)
    void updateRoleFromRequest(@MappingTarget Role role, RoleUpdateRequest roleUpdateRequest);
}
