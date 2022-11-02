package com.example.pbl_api.service.impl;

import com.example.pbl_api.entity.WebsiteVisitor;
import com.example.pbl_api.model.ReportsModel;
import com.example.pbl_api.project_interface.IReports;
import com.example.pbl_api.repository.ReportsRepository;
import com.example.pbl_api.repository.WebsiteVisitorRepository;
import com.example.pbl_api.service.IReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReportsService implements IReportsService {

    @Autowired
    ReportsRepository reportsRepository;

    @Autowired
    WebsiteVisitorRepository websiteVisitorRepository;

    @Override
    public ReportsModel getWeeksSalesReports() {
        List<IReports> reports = reportsRepository.getWeeksSalesReports();
        return mapIReportToReportModel(reports);
    }

    @Override
    public ReportsModel getWeeksRevenueReports() {
        List<IReports> reports = reportsRepository.getWeeksRevenueReports();
        return mapIReportToReportModel(reports);
    }

    @Override
    public ReportsModel getWeeksProductsReports() {
        List<IReports> reports = reportsRepository.getWeeksProductsReports();
        return mapIReportToReportModel(reports);
    }

    @Override
    public ReportsModel getTotalReports() {
        List<IReports> reports = reportsRepository.getTotalReports();
        return mapIReportToReportModel(reports);
    }

    @Override
    public ReportsModel getWeeksVisitorsReports() {
        List<IReports> reports = reportsRepository.getWeeksVisitorsReports();
        return mapIReportToReportModel(reports);
    }

    @Override
    public void countingVisitor() {
        WebsiteVisitor websiteVisitor = websiteVisitorRepository.getWebsiteVisitorNow();
        if(websiteVisitor!=null){
            websiteVisitor.setCounter(websiteVisitor.getCounter()+1);
            websiteVisitorRepository.save(websiteVisitor);
        }
        else {
            websiteVisitorRepository.insertNewWeek();
        }
    }

    ReportsModel mapIReportToReportModel(List<IReports> reports){
        List<String> labels=new ArrayList<>();
        List<Long> data=new ArrayList<>();
        for (IReports report :
                reports) {
            labels.add(report.getLabel());
            data.add(report.getData());
        }
        return new ReportsModel(labels,data);
    }
}
