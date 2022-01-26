package com.learning.springtransactionmgmtinterceptordemo.service;

import com.learning.springtransactionmgmtinterceptordemo.domain.Retailer;
import com.learning.springtransactionmgmtinterceptordemo.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetailerService {
    
    @Autowired
    private RetailerRepository retailerRepository;

    public void addRetailer(Retailer retailer) {
        retailerRepository.save(retailer);
    }

    public void updateRetailer(Retailer retailer) {
        retailerRepository.save(retailer);
    }

    public void deleteRetailer(Long id) {
        retailerRepository.deleteById(id);
    }
}
