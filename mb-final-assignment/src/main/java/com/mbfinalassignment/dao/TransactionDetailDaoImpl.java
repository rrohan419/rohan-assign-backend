package com.mbfinalassignment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbfinalassignment.entity.TransactionDetail;
import com.mbfinalassignment.exceptionHandling.CustomException;
import com.mbfinalassignment.exceptionHandling.ErrorCode;
import com.mbfinalassignment.repository.TransactionDetailRepository;

@Service
public class TransactionDetailDaoImpl implements TransactionDetailDao {

	@Autowired
	TransactionDetailRepository detailRepository;

	@Override
	public List<TransactionDetail> getAllTransaction() {
		try {
			return detailRepository.findAll();
		} catch (Exception ex) {
			throw new CustomException("Error featching transaction detail data", ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public TransactionDetail saveDetails(TransactionDetail transactionDetail) {
		try {
			return detailRepository.save(transactionDetail);
		} catch (Exception e) {
			throw new CustomException("Error saving transaction details", ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

}
