package com.example.twowaychoice;

import com.example.twowaychoice.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)// todo 重要
public class TwoWayChoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoWayChoiceApplication.class, args);
    }

}
