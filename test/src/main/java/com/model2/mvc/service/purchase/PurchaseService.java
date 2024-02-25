package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	public void addPurchase(Purchase purchaseVO) throws Exception;
	
	public Purchase getPurchase(int prodNo) throws Exception;
//	public PurchaseVO getPurchase(int prodNo, String tranCode) throws Exception;
	public Purchase getPurchaseByTranNo(int tranNo) throws Exception;
	
	public Map<String,Object> getPurchaseList(String buyer_id, SearchVO searchVO) throws Exception;
	public Map<String,Object> getPurchaseListProdNo(String buyer_id,String tranCode) throws Exception;
	
	public void updatePurchase(Purchase purchaseVO) throws Exception;
}
