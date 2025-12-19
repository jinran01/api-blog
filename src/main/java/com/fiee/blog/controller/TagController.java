package com.fiee.blog.controller;


import com.fiee.blog.annotation.OptLog;
import com.fiee.blog.enums.LogType;
import com.fiee.blog.service.TagService;
import com.fiee.blog.vo.Result;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Author: Fiee
 * @ClassName: TestControoler
 * @Date: 2025/12/17
 * @Version: v1.0.0
 **/
@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    @Autowired
    private TagService tagService;


    @Operation(summary = "文章标签",description = "通过id查询标签",method = "GET",tags = "后台文章标签模块")
    @OptLog(value = "文章标签",type = LogType.QUERY)
    @Parameters(value = {@Parameter(name = "id", description = "标签id")})
    @GetMapping("/{id}")
    public Result getTagById(@PathVariable("id") Integer id){
        return Result.ok(tagService.getTagById(id));
    }

}
