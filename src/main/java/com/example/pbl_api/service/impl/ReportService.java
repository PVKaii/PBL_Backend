package com.example.pbl_api.service.impl;

import com.example.pbl_api.model.ReportModel;
import com.example.pbl_api.project_interface.IReport;
import com.example.pbl_api.repository.ReportRepository;
import com.example.pbl_api.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReportService implements IReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public ReportModel getSoldCategoryReportByDay(String startDay, String endDay) {
        List<String> labels=new ArrayList<>();
        List<Long> data=new ArrayList<>();
        List<IReport> reports = reportRepository.findSoldCategoryReportByDay(startDay,endDay);
        for (IReport report :
             reports) {
            labels.add(report.getName());
            data.add(report.getCount());
        }
        return new ReportModel(labels,data);
    }
}
