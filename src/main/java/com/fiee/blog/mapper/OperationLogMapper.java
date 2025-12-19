package com.fiee.blog.mapper;

import com.fiee.blog.entity.OperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fiee.blog.vo.ConditionVO;
import com.fiee.blog.vo.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Fiee
* @description 针对表【tb_operation_log】的数据库操作Mapper
* @createDate 2025-12-17 17:44:07
* @Entity com.fiee.blog.entity.OperationLog
*/
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
    /**
     * 获取所有操作日志
     * @return
     */
    List<OperationLog> getOperationLogs(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);
}




