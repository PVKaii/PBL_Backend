package com.example.pbl_api.service.impl;

import com.example.pbl_api.entity.Bill;
import com.example.pbl_api.entity.BillDetail;
import com.example.pbl_api.entity.Product;
import com.example.pbl_api.entity.User;
import com.example.pbl_api.model.BillModel;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.repository.BillDetailRepository;
import com.example.pbl_api.repository.BillRepository;
import com.example.pbl_api.repository.ProductRepository;
import com.example.pbl_api.repository.UserRepository;
import com.example.pbl_api.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Service
public class BillServiceImpl implements BillService {


    @Autowired
    BillRepository billRepository;

    @Autowired
    BillDetailRepository billDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<BillModel> getAllBills() {
        List<BillModel> result = ((List<Bill>) billRepository.findAll()).stream()
                .map( bill -> new BillModel(bill)).toList();
        return result;
    }

    @Override
    public List<BillModel> getBillsByUser(int idUser) {
        return null;
    }

    @Override
    public BillModel saveBill(List<ProductModel> productList,int idUser,double total) {
        User user =userRepository.findUserById(idUser);
        Bill bill= new Bill(total, null,user);
        List<BillDetail> billDetailList= new ArrayList<>();
        for (ProductModel productModel:
             productList) {
            Product product = productRepository.findProductById(productModel.getId());
            billDetailList.add(new BillDetail(product,bill));
        }
        bill.setBillDetailSet(new HashSet<>(billDetailList));
        billRepository.save(bill);
        billDetailRepository.saveAll(billDetailList);
        return new BillModel(bill);
    }

    @Override
    public BillModel getBillById(long id) {
        return null;
    }
}
