package com.mbfinalassignment.dao;

import java.util.List;

import com.mbfinalassignment.entity.TransactionDetail;

public interface TransactionDetailDao {
	List<TransactionDetail> getAllTransaction();
	TransactionDetail saveDetails(TransactionDetail transactionDetail);
}
