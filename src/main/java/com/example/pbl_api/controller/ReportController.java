package com.example.pbl_api.controller;


import com.example.pbl_api.model.ReportModel;
import com.example.pbl_api.project_interface.IReport;
import com.example.pbl_api.repository.ReportRepository;
import com.example.pbl_api.service.impl.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    ReportService reportService;


    @GetMapping("")
    public ResponseEntity<?> getSoldCategoryReportByDay(@RequestParam(name = "start") String startDay,@RequestParam(name = "end") String endDay){
        ReportModel reportModel= reportService.getSoldCategoryReportByDay(startDay,endDay);
        return new ResponseEntity<>(reportModel, HttpStatus.OK);
    }
}
