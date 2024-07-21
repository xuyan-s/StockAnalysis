package com.xuyan.stock.mapper;

import com.xuyan.stock.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
* @author 14562
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-07-20 16:36:55
* @Entity com.xuyan.stock.pojo.entity.SysUser
*/
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findUserInfoByUserName(@Param("userName") String userName);

}
