package com.fiee.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fiee.blog.entity.Role;
import com.fiee.blog.service.RoleService;
import com.fiee.blog.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Fiee
* @description 针对表【tb_role】的数据库操作Service实现
* @createDate 2025-12-19 19:10:07
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




