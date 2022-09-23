package com.example.pbl_api.service.impl;

import com.example.pbl_api.repository.BrandRepository;
import com.example.pbl_api.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements IBrandService {
    @Autowired
    BrandRepository brandRepository;
}
