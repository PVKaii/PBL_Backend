package com.example.pbl_api.service;

import com.example.pbl_api.model.ReportsModel;

public interface IReportsService {
    ReportsModel getWeeksSalesReports();

    ReportsModel getWeeksRevenueReports();

    ReportsModel getWeeksProductsReports();

    ReportsModel getTotalReports();

    ReportsModel getWeeksVisitorsReports();

    void countingVisitor();
}
