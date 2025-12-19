package com.fiee.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fiee.blog.entity.OperationLog;
import com.fiee.blog.service.OperationLogService;
import com.fiee.blog.mapper.OperationLogMapper;
import com.fiee.blog.utils.PageUtils;
import com.fiee.blog.vo.ConditionVO;
import com.fiee.blog.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
* @author Fiee
* @description 针对表【tb_operation_log】的数据库操作Service实现
* @createDate 2025-12-17 17:44:07
*/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>
    implements OperationLogService{
    @Autowired
    KafkaTemplate<String, OperationLog> kafkaTemplate;
    @Autowired
    private OperationLogMapper operationLogMapper;
    // 阻塞队列
    private final BlockingQueue<OperationLog> logQueue = new LinkedBlockingQueue<>(5000);
    // 批量大小
    private static final int BATCH_SIZE = 100;
    // 每两秒执行一次
    private static final long FLUSH_INTERVAL_MS = 2000;

    /**
     * 异步保存操作日志
     * @param log 日志
     */
//    @Override
//    @Async(value = "logTaskExecutor")
//    public void saveOperationLogAsync(OperationLog log) {
//        // 非阻塞入队
//        logQueue.offer(log);
//    }

    /**
     * 定时任务批量保存日志
     */
//    @Scheduled(fixedRate = FLUSH_INTERVAL_MS)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void flushLogs() {
//        List<OperationLog> batch = new ArrayList<>(BATCH_SIZE);
//        int drained = logQueue.drainTo(batch, BATCH_SIZE); // 批量取出
//        if (drained > 0) {
//            // 批量保存
//            this.saveBatch(batch);
//        }
//    }
    /**
     * 日志生产
     * 发送kafka消息保存日志
     * @param log 日志
     */
    @Async("logTaskExecutor")
    public void sendToKafkaToSaveLog(OperationLog log) {
        kafkaTemplate.send("log-topic", log);
    }

    /**
     * @return
     */
    @Override
    public PageResult<OperationLog>getOperationLogs(ConditionVO conditionVO) {
        List<OperationLog> operationLogs = operationLogMapper.getOperationLogs(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        // TODO 根据条件获取日志总数
//        operationLogMapper.getCount(conditionVO);
        return new PageResult<>(operationLogs,200);
    }
    /**
     * 日志消费
     * 日志持久化
     * @param log
     */
    @KafkaListener(topics = "log-topic")
    public void consumeLog(OperationLog log) {
        this.save(log);
    }

    @Override
    public void deleteOperationLogs(List<Integer> logIds) {

    }

}




