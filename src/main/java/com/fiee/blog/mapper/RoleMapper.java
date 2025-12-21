package com.fiee.blog.mapper;

import com.fiee.blog.dto.ResourceRoleDTO;
import com.fiee.blog.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Fiee
* @description 针对表【tb_role】的数据库操作Mapper
* @createDate 2025-12-19 19:10:07
* @Entity com.fiee.blog.entity.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询角色资源权限
     * @return
     */
    List<ResourceRoleDTO> listResourceRoles();
    List<String> listRolesByUserAuthId(Integer userAuthId);
}




