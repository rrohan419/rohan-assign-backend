package com.mbfinalassignment.service;

import java.util.List;

import com.mbfinalassignment.entity.TransactionDetail;
import com.mbfinalassignment.model.TransactionDetailModel;

public interface TransactionDeailsService {
	List<TransactionDetail> getAllTransactions();

	TransactionDetail saveTransaction(TransactionDetailModel model);
}
