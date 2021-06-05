package com.hanzoy.yuekewei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.hanzoy.yuekewei.mapper")
@EnableScheduling  //Springboot支持定时任务
public class YuekeweiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuekeweiApplication.class, args);
    }

}
