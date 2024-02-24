package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class PurchaseServiceImpl implements PurchaseService{
	PurchaseDao purchaseDao = new PurchaseDao();
	
	public void addPurchase(PurchaseVO purchaseVO) throws Exception{
		purchaseDao.addPurchase(purchaseVO);
	}
	
	public PurchaseVO getPurchase(int prodNo) throws Exception{
		return purchaseDao.getPurchase(prodNo);
	}
	
//	public PurchaseVO getPurchase(int prodNo, String tranCode) throws Exception{
//		return purchaseDao.getPurchase(prodNo, tranCode);
//	}
	public PurchaseVO getPurchaseByTranNo(int tranNo) throws Exception{
		return purchaseDao.getPurchaseByTranNo(tranNo);
	}
	
	public Map<String,Object> getPurchaseList(String buyer_id) throws Exception{
		return purchaseDao.getPurchaseList(buyer_id);
	}
	
	
	public Map<String,Object> getPurchaseListProdNo(String buyer_id, String tranCode) throws Exception{
		return purchaseDao.getPurchaseListProdNo(buyer_id,tranCode);
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception{
		purchaseDao.updatePurchase(purchaseVO);
	}
}
