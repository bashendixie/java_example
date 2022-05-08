package com.home.skydance.service;

import com.home.skydance.dao.UserMapper;
import com.home.skydance.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 定义自动执行的任务
 */
@Component
@EnableScheduling
@Transactional(rollbackFor = Throwable.class)
public class CronService {

    private static Object LOCK = new Object();
    private final static Logger logger = LoggerFactory.getLogger(CronService.class);
    private final UserMapper userMapper;
    public CronService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //每个线程每次查询的条数
    private static final Integer LIMIT = 5000;
    //核心线程数
    private static final Integer CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();// * 2;
    //最大线程数
    private static final Integer MAXIMUM_POOl_SIZE = CORE_POOL_SIZE;
    //创建线程池
    private ThreadPoolExecutor pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOl_SIZE * 2, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));

    /**
     * 定义每天凌晨一点执行的任务
     */
    //@Scheduled(cron = "0 0 1 * * ?")
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void checkUsersTimeTask() throws ExecutionException, InterruptedException {
        System.out.println("定时任务开始");
        //计数器，用于处理分页数据
        int count = 0;
        //获取所有待处理数据总数
        Integer total = userMapper.countPreRecordsByDate(getMaxCurrentDate());
        logger.info("待处理数据条数：{}", total);
        //计算需要多少轮才能处理完
        int num = total / (LIMIT * CORE_POOL_SIZE) + 1;
        logger.info("要经过的轮数:{}", num);
        //统计处理成功的数据条数
        int finishCount = 0;
        for (int i = 0; i < num; i++)
        {
            logger.info("第" + (i+1) + "轮开始");
            //接收线程返回结果
            List<Future<Integer>> futureList = new ArrayList<>(32);
            //起CORE_POOL_SIZE个线程并行查询更新库，加锁
            for (int j = 0; j < CORE_POOL_SIZE; j++) {
                synchronized (LOCK) {
                    int start = count * LIMIT;
                    count++;
                    //提交线程，用数据起始位置标识线程
                    Future<Integer> future = pool.submit(new MarkDataTask(start, LIMIT));
                    //先不取值，防止阻塞,放进集合
                    futureList.add(future);
                }
            }
            //统计处理成功的数据
            for (Future f : futureList) {
                finishCount = finishCount + (int)f.get();
            }
            logger.info("第" + (i+1) + "轮结束");
        }
        logger.info("当天定时任务完成，预处理数据:{},处理成功：{}", total, finishCount);
        System.out.println("定时任务结束");
    }

    public static Date getMaxCurrentDate() {
        LocalDate localDate = LocalDate.now(); //获取今天的日期
        //LocalDate yesterday = localDate.plusDays(-1); //前一天日期是今天减1
        LocalDate yesterday = localDate;
        LocalDateTime startTime = LocalDateTime.of(yesterday, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(yesterday, LocalTime.MAX);
        return Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 批量处理数据线程类
     */
    class MarkDataTask implements Callable<Integer> {
        int start;
        int limit;

        MarkDataTask(int start, int limit) {
            this.start = start;
            this.limit = limit;
        }

        @Override
        public Integer call() {
            //设置线程名字
            Thread.currentThread().setName("Thread" + start);
            int count = 0;
            //获取当前线程需要处理的数据
            List<User> usersList = userMapper.selectByDateTime(getMaxCurrentDate(), start, limit);
            if (CollectionUtils.isEmpty(usersList)) {
                return count;
            }
            logger.info("操作第：[{},{}]数据", start, ""+(start + limit));
            // 这里进行处理数据并更新数据库开始
            for(int i=0; i<usersList.size(); i++)
            {
                usersList.get(i).setSvdValue(usersList.get(i).getId() * 3 - 3);
                usersList.get(i).setThreadNo(Thread.currentThread().getName());
                usersList.get(i).setUpdateTime(new Date());
            }
            // 这里进行处理数据并更新数据库结束
            userMapper.updateForeach(usersList);
            logger.info("操作第：[{},{}]数据完成！", start, start + limit);
            return usersList.size();
        }
    }
}
