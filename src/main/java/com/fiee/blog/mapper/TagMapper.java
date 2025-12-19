package com.fiee.blog.mapper;

import com.fiee.blog.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Fiee
* @description 针对表【tb_tag】的数据库操作Mapper
* @createDate 2025-12-17 15:34:32
* @Entity com.fiee.blog.entity.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}




