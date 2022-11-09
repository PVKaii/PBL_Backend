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
import com.example.pbl_api.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class BillService implements IBillService {


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
        List<BillModel> result = ((List<Bill>) billRepository.findAllByOrderByIdDesc()).stream()
                .map( bill -> new BillModel(bill)).toList();
        return result;
    }

    @Override
    public List<BillModel> getBillsByUser(long idUser) {
        List<BillModel> result = ((List<Bill>) billRepository.findBillsByUserId(idUser)).stream()
                .map( bill -> new BillModel(bill)).toList();
        return result;
    }

    @Override
    public BillModel saveBill(List<ProductModel> productList,int idUser,double total,boolean type,List<Integer> productsAmountList) {
//        User user =userRepository.findUserById(idUser);
//        Bill bill= new Bill(total, LocalDate.now(),user,type);
//        List<BillDetail> billDetailList= new ArrayList<>();
//        for(int i =0;i<productList.size();i++){
//            ProductModel productModel = productList.get(i);
//            Product product = productRepository.findProductById(productModel.getId());
//            int amount = productsAmountList.get(i);
//            double totalPayable =product.getPrice()*amount;
//            billDetailList.add(new BillDetail(product,bill,amount,totalPayable));
//        }
//        bill.setBillDetailSet(new HashSet<>(billDetailList));
//        billRepository.save(bill);
//        billDetailRepository.saveAll(billDetailList);
//        return new BillModel(bill);
        return null;
    }

    @Override
    public BillModel getBillById(long id) {
        return null;
    }
}
