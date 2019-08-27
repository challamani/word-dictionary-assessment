package com.happiestminds.assessment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncThreadPoolConfig {

    private static Logger logger = LoggerFactory.getLogger(AsyncThreadPoolConfig.class);

    @Value("${async.task-executor.core-pool-size}")
    private Integer corePoolSize;

    @Value("${async.task-executor.max-pool-size}")
    private Integer maxPoolSize;

    @Value("${async.task-executor.queue-capacity}")
    private Integer queueCapacity;

    @Value("${async.task-executor.thread-name-prefix}")
    private String threadNamePrefix;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new RejectedTaskHandler());
        executor.initialize();
        return executor;
    }


    final static class RejectedTaskHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //Rejected
            logger.info("Rejected One :: {}", r.toString());
            logger.info("Thread Pool :: {}", executor.toString());
        }
    }
}
