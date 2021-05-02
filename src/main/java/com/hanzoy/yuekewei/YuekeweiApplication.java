package com.hanzoy.yuekewei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanzoy.yuekewei.mapper")
public class YuekeweiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuekeweiApplication.class, args);
    }

}
