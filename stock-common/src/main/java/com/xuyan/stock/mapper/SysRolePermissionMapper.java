package com.xuyan.stock.mapper;

import com.xuyan.stock.pojo.entity.SysRolePermission;

/**
* @author 14562
* @description 针对表【sys_role_permission(角色权限表)】的数据库操作Mapper
* @createDate 2024-07-20 16:36:55
* @Entity com.xuyan.stock.pojo.entity.SysRolePermission
*/
public interface SysRolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

}
