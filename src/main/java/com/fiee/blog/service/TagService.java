package com.fiee.blog.service;

import com.fiee.blog.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fiee.blog.vo.Result;

import java.util.List;

/**
* @author Fiee
* @description 针对表【tb_tag】的数据库操作Service
* @createDate 2025-12-17 15:34:32
*/
public interface TagService extends IService<Tag> {
    /**
     * 根据标签id查询标签
     * @param tagId
     * @return
     */
    Tag getTagById(Integer tagId);
}
