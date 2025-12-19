package com.fiee.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fiee.blog.entity.UserInfo;
import com.fiee.blog.service.UserInfoService;
import com.fiee.blog.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Fiee
* @description 针对表【tb_user_info】的数据库操作Service实现
* @createDate 2025-12-20 02:33:55
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




