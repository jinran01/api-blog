package com.fiee.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fiee.blog.entity.UserAuth;
import com.fiee.blog.service.UserAuthService;
import com.fiee.blog.mapper.UserAuthMapper;
import org.springframework.stereotype.Service;

/**
* @author Fiee
* @description 针对表【tb_user_auth】的数据库操作Service实现
* @createDate 2025-12-20 21:16:29
*/
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth>
    implements UserAuthService{

}




