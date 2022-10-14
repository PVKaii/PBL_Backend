package com.example.pbl_api.service.impl;

import com.example.pbl_api.model.ReportModel;
import com.example.pbl_api.project_interface.IDayReport;
import com.example.pbl_api.project_interface.IMonthReport;
import com.example.pbl_api.project_interface.IProductReport;
import com.example.pbl_api.repository.ReportRepository;
import com.example.pbl_api.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<IProductReport> reports = reportRepository.findSoldCategoryReportByDay(startDay,endDay);
        for (IProductReport report :
             reports) {
            labels.add(report.getName());
            data.add(report.getCount());
        }
        return new ReportModel(labels,data);
    }

    @Override
    public ReportModel getDayReport(String startDay, String endDay) {
        List<String> labels=new ArrayList<>();
        List<Long> data=new ArrayList<>();
        List<IDayReport> reports = reportRepository.getDayReport(startDay,endDay);
        for (IDayReport report :
                reports) {
            labels.add(report.getDay().toString());
            data.add(report.getTotal());
        }
        return new ReportModel(labels,data);
    }

    @Override
    public ReportModel getMonthReport() {
        List<String> labels=new ArrayList<>();
        List<Long> data=new ArrayList<>();
        List<IMonthReport> reports = reportRepository.getMonthReport();
        for (IMonthReport report :
                reports) {
            labels.add(report.getMonth());
            data.add(report.getTotal());
        }
        return new ReportModel(labels,data);
    }
}
