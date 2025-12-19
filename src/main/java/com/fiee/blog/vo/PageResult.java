package com.fiee.blog.vo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T> {

    /**
     * 分页列表
     */
    @Schema(name = "recordList", description = "分页列表")
    private List<T> recordList;

    /**
     * 总数
     */
    @Schema(name = "count", description = "总数")
    private Integer count;

}