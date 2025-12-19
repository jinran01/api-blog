package com.fiee.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fiee.blog.entity.Tag;
import com.fiee.blog.service.TagService;
import com.fiee.blog.mapper.TagMapper;
import com.fiee.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Fiee
* @description 针对表【tb_tag】的数据库操作Service实现
* @createDate 2025-12-17 15:34:32
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{
    @Autowired
    private TagMapper tagMapper;
    @Override
    public Tag getTagById(Integer tagId) {
//        Tag tag = tagMapper.selectById(tagId);
        return tagMapper.selectById(tagId);
    }
}




