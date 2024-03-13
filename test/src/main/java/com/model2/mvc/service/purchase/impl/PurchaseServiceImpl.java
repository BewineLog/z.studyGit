package com.model2.mvc.service.purchase.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.dao.PurchaseDao;
import com.model2.mvc.service.domain.Purchase;


@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	PurchaseDao purchaseDao;
	
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	public int addPurchase(Purchase Purchase) throws Exception{
		return purchaseDao.addPurchase(Purchase);
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
	
	public List<Object> getPurchaseList(String buyer_id,Page page) throws Exception{
		return purchaseDao.getPurchaseList(buyer_id, page);
	}
	
//	
//	public Map<String,Object> getPurchaseListProdNo(String buyer_id, String tranCode) throws Exception{
//		return purchaseDao.getPurchaseListProdNo(buyer_id,tranCode);
//	}
	
	public int getTotalCount(String buyer_id) throws Exception{
		return purchaseDao.getTotalCount(buyer_id);
	}
	
	public int updatePurchase(Purchase Purchase) throws Exception{
		return purchaseDao.updatePurchase(Purchase);
	}
	
	public int deletePurchase(int tranNo) throws Exception{
		return purchaseDao.deletePurchase(tranNo);
	}
}
