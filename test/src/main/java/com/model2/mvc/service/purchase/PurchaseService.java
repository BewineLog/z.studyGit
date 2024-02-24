package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public interface PurchaseService {
	public void addPurchase(PurchaseVO purchaseVO) throws Exception;
	
	public PurchaseVO getPurchase(int prodNo) throws Exception;
//	public PurchaseVO getPurchase(int prodNo, String tranCode) throws Exception;
	public PurchaseVO getPurchaseByTranNo(int tranNo) throws Exception;
	
	public Map<String,Object> getPurchaseList(String buyer_id, SearchVO searchVO) throws Exception;
	public Map<String,Object> getPurchaseListProdNo(String buyer_id,String tranCode) throws Exception;
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception;
}
