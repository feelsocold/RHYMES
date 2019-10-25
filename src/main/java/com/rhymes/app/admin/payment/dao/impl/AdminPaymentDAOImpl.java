package com.rhymes.app.admin.payment.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rhymes.app.admin.payment.dao.AdminPaymentDAO;
import com.rhymes.app.admin.payment.model.AdminPaymentDetailDTO;
import com.rhymes.app.payment.model.PaymentDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AdminPaymentDAOImpl implements AdminPaymentDAO {
	
	@Autowired
	SqlSession SqlSession;
	String ap = "adminPayment.";

	// 관리자페이지 결제내역조회
	@Override
	public List<PaymentDTO> getOrderSuccess() {
		List<PaymentDTO> orderSuccess = SqlSession.selectList(ap + "getOrderSuccess");
		return orderSuccess;
	}

	// 관리자페이지 결제상상세내역조회
	@Override
	public List<AdminPaymentDetailDTO> getOrderDetail(PaymentDTO dto) {
		List<AdminPaymentDetailDTO> orderDetail = SqlSession.selectList(ap + "getOrderDetail", dto);
		return orderDetail;
	}

}
