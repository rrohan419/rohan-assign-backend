package com.mbfinalassignment.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbfinalassignment.dao.TransactionDetailDao;
import com.mbfinalassignment.entity.TransactionDetail;
import com.mbfinalassignment.exceptionHandling.CustomException;
import com.mbfinalassignment.exceptionHandling.ErrorCode;
import com.mbfinalassignment.model.TransactionDetailModel;

@Service
public class TransactionDeailsServiceImpl implements TransactionDeailsService {

	@Autowired
	private TransactionDetailDao transactionDetailDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<TransactionDetail> getAllTransactions() {
		List<TransactionDetail> alltransaction = transactionDetailDao.getAllTransaction();
		if (alltransaction.isEmpty()) {
			throw new CustomException("database is empty", ErrorCode.NOT_FOUND);
		}
		return alltransaction;
	}

	@Override
	public TransactionDetail saveTransaction(TransactionDetailModel model) {
		TransactionDetail detail = mapper.map(model, TransactionDetail.class);
		return transactionDetailDao.saveDetails(detail);
	}

}
