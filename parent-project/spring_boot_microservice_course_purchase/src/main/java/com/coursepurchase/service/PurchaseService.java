package com.coursepurchase.service;

import com.coursepurchase.model.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase savePurchase(Purchase purchase);

    List<Purchase> findAllPurchasesofUser(Long userId);
}
