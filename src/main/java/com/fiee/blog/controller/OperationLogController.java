package com.fiee.blog.controller;

import com.fiee.blog.annotation.AccessLimit;
import com.fiee.blog.annotation.OptLog;
import com.fiee.blog.enums.LogType;
import com.fiee.blog.service.OperationLogService;
import com.fiee.blog.vo.ConditionVO;
import com.fiee.blog.vo.PageResult;
import com.fiee.blog.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Fiee
 * @ClassName: OperationLogController
 * @Date: 2025/12/19
 * @Version: v1.0.0
 **/
@RestController
@RequestMapping("/api/v1/log")
public class OperationLogController {
    @Autowired
    private OperationLogService operationLogService;
    /**
     * 获取操作日志列表
     * @return
     */
    @Operation(summary = "获取操作日志列表",description = "获取操作日志列表",
            method = "GET",tags = "后台操作日志模块")
    @AccessLimit(seconds = 60,maxCount = 100)
    @GetMapping("/all")
    public Result<PageResult> getLogList(ConditionVO conditionVO){
        return Result.ok(operationLogService.getOperationLogs(conditionVO));
    }
    @Operation(summary = "删除操作日志",description = "删除操作日志",
            method = "DELETE",tags = "后台操作日志模块")
    @OptLog(value = "删除操作日志",type = LogType.DELETE)
    @AccessLimit(seconds = 60,maxCount = 20)
    @DeleteMapping("/delete")
    public Result<?> deleteLog(@RequestBody List<Integer>  ids){
        return operationLogService.removeBatchByIds(ids) ? Result.ok() : Result.fail(false,"删除失败");
    }
}
