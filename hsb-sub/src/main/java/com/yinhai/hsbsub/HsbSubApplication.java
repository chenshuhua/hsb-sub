package com.yinhai.hsbsub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.yinhai.hsbsub.repository"})
public class HsbSubApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsbSubApplication.class, args);
    }

}
