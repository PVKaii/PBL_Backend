package com.example.pbl_api.config;

import com.example.pbl_api.model.Brand;
import com.example.pbl_api.repository.BrandRepository;
import com.example.pbl_api.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInitializer implements ApplicationRunner {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(((List<Brand>)brandRepository.findAll()).size()==0) System.out.println("null");
         brandRepository.save(new Brand("haha"));

    }
}
