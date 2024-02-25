package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.dao.PurchaseDao;
import com.model2.mvc.service.domain.Purchase;


public class PurchaseServiceImpl implements PurchaseService{
	PurchaseDao purchaseDao = new PurchaseDao();
	
	public void addPurchase(Purchase Purchase) throws Exception{
		purchaseDao.addPurchase(Purchase);
	}
	
	public Purchase getPurchase(int prodNo) throws Exception{
		return purchaseDao.getPurchase(prodNo);
	}
	
//	public Purchase getPurchase(int prodNo, String tranCode) throws Exception{
//		return purchaseDao.getPurchase(prodNo, tranCode);
//	}
	public Purchase getPurchaseByTranNo(int tranNo) throws Exception{
		return purchaseDao.getPurchaseByTranNo(tranNo);
	}
	
	public Map<String,Object> getPurchaseList(String buyer_id,SearchVO searchVO) throws Exception{
		return purchaseDao.getPurchaseList(buyer_id, searchVO);
	}
	
	
	public Map<String,Object> getPurchaseListProdNo(String buyer_id, String tranCode) throws Exception{
		return purchaseDao.getPurchaseListProdNo(buyer_id,tranCode);
	}
	
	public void updatePurchase(Purchase Purchase) throws Exception{
		purchaseDao.updatePurchase(Purchase);
	}
}
