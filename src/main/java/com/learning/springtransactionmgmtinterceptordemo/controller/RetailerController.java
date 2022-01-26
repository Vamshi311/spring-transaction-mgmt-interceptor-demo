package com.learning.springtransactionmgmtinterceptordemo.controller;

import com.learning.springtransactionmgmtinterceptordemo.domain.Retailer;
import com.learning.springtransactionmgmtinterceptordemo.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retailer")
public class RetailerController {
    
    @Autowired
    private RetailerService retailerService;

    @PostMapping("/add")
    public void addRetailer(@RequestBody Retailer retailer) {
        retailerService.addRetailer(retailer);
    }

    @PutMapping("/update")
    public void updateRetailer(@RequestBody Retailer retailer) {
        retailerService.updateRetailer(retailer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRetailer(@PathVariable(value = "id", required = true) Long id) {
        retailerService.deleteRetailer(id);
    }
}
