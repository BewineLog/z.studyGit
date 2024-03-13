package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.dao.PurchaseDao;

public interface PurchaseService {
	
	public void setPurchaseDao(PurchaseDao purchaseDao);
	
	public int addPurchase(Purchase purchaseVO) throws Exception;
	
	public Purchase getPurchase(int prodNo) throws Exception;
//	public PurchaseVO getPurchase(int prodNo, String tranCode) throws Exception;
	public Purchase getPurchaseByTranNo(int tranNo) throws Exception;
	
	public List<Object> getPurchaseList(String buyer_id, Page page) throws Exception;
//	public Map<String,Object> getPurchaseListProdNo(String buyer_id,String tranCode) throws Exception;
	
	public int getTotalCount(String buyer_id) throws Exception;
	
	public int updatePurchase(Purchase purchaseVO) throws Exception;
	
	public int deletePurchase(int tranNo) throws Exception;
}
