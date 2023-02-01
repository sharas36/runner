package com.services;

import org.springframework.stereotype.Service;

@Service
public class DealerService extends MainService{

    private int dealerId = 1;
    private Dealer dealer = dealerRepository.getById(dealerId);

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

}
