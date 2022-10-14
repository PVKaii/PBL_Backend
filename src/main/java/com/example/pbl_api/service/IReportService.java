package com.example.pbl_api.service;

import com.example.pbl_api.model.ReportModel;

public interface IReportService {
    ReportModel  getSoldCategoryReportByDay(String startDay,String endDay);

    ReportModel  getDayReport(String startDay,String endDay);

    ReportModel  getMonthReport();
}
