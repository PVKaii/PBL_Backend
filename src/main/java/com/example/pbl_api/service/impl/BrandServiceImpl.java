package com.example.pbl_api.service.impl;

import com.example.pbl_api.repository.BrandRepository;
import com.example.pbl_api.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;
}
