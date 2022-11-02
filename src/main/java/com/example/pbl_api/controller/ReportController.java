package com.example.pbl_api.controller;


import com.example.pbl_api.service.impl.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
public class ReportController {

    @Autowired
    ReportsService reportsService;

    @GetMapping("/sales")
    public ResponseEntity<?> getWeeksSalesReports(){
        return new ResponseEntity<>(reportsService.getWeeksSalesReports(), HttpStatus.OK);
    }

    @GetMapping("/revenue")
    public ResponseEntity<?> getWeeksRevenueReports(){
        return new ResponseEntity<>(reportsService.getWeeksRevenueReports(), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getWeeksProductsReports(){
        return new ResponseEntity<>(reportsService.getWeeksProductsReports(), HttpStatus.OK);
    }

    @GetMapping("/visitor")
    public ResponseEntity<?> getWeeksVisitorsReports(){
        return new ResponseEntity<>(reportsService.getWeeksVisitorsReports(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getTotalReports(){
        return new ResponseEntity<>(reportsService.getTotalReports(), HttpStatus.OK);
    }

    @PostMapping ("")
    public ResponseEntity<?> countingVisitor(){
        reportsService.countingVisitor();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
