package com.fiee.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiee.blog.entity.OperationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fiee.blog.vo.ConditionVO;
import com.fiee.blog.vo.PageResult;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
* @author Fiee
* @description 针对表【tb_operation_log】的数据库操作Service
* @createDate 2025-12-17 17:44:07
*/
public interface OperationLogService extends IService<OperationLog> {
    /**
     * 保存操作日志
     * @param log 日志
     */
//    void saveOperationLogAsync(OperationLog log);

    /**
     * 日志消费
     * 日志持久化
     * @param log
     */
    void sendToKafkaToSaveLog(OperationLog log);

    /**
     * 获取所有操作日志
     * @return
     */
    PageResult<OperationLog> getOperationLogs(ConditionVO conditionVO);

    /**
     * 删除操作日志
     * @param logIds
     */
    void deleteOperationLogs(List<Integer> logIds);
}
