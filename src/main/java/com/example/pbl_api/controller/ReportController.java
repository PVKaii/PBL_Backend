package com.example.pbl_api.controller;


import com.example.pbl_api.model.ReportModel;
import com.example.pbl_api.service.impl.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    ReportService reportService;



    @GetMapping("")
    public ResponseEntity<?> getDayReport(@RequestParam(name = "start",required = false) String startDay,@RequestParam(name = "end",required = false) String endDay
    ,@RequestParam(name = "year",required = false) String year,@RequestParam(name = "product",required = false) String product
    ) throws Exception {
        if(year==null) year="";
        if(year.equals("true") &&startDay==null&&endDay==null&&product==null){
            ReportModel reportModel= reportService.getMonthReport();
            return new ResponseEntity<>(reportModel, HttpStatus.OK);
        }
        else{
            if(startDay!=null&&endDay!=null){
                if(product==null) product="";
                if(product.equals("true")){
                    ReportModel reportModel= reportService.getSoldCategoryReportByDay(startDay,endDay);
                    return new ResponseEntity<>(reportModel, HttpStatus.OK);
                }
                else{
                    ReportModel reportModel= reportService.getDayReport(startDay,endDay);
                    return new ResponseEntity<>(reportModel, HttpStatus.OK);
                }
            }
        }
        throw new Exception();
    }
}
