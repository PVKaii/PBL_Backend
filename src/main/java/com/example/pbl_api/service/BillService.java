package com.example.pbl_api.service;

import com.example.pbl_api.model.BillDetailModel;
import com.example.pbl_api.model.BillModel;
import com.example.pbl_api.model.ProductModel;

import java.util.List;

public interface BillService {

    List<BillModel> getAllBills();

    List<BillModel> getBillsByUser(int idUser);

    BillModel saveBill(List<ProductModel> productList,int idUser,double total);

    BillModel getBillById(long id);


}
