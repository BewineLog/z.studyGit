package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.SqlSessionFactoryBean;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.dao.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession ;
	
	public ProductDaoImpl() {}

	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("ProductDao set SqlSession::");
		this.sqlSession = sqlSession;
	}
	public int addProduct(Product product) throws Exception{
		return sqlSession.insert("ProductMapper.insertProduct", product);
	}
	
	public Product getProduct(int prodNo) throws Exception{
		return sqlSession.selectOne("ProductMapper.selectProduct", prodNo) ;		
	}
	
	public List<Object> getProductList(SearchVO search) throws Exception{
		List<Object> list = sqlSession.selectList("ProductMapper.getProductList", search);
		
		for(int i = 0 ; i < list.size() ; i++) {
			Product product=  (Product)list.get(i);
			
			if(product.getTranCode() == null) {
				product.setProTranCode("판매중");
				list.set(i, (Object)product);
			}else if(product.getTranCode().equals("1")) {
				product.setProTranCode("구매완료");
				list.set(i, (Object)product);
			}else if(product.getTranCode().equals("2")) {
				product.setProTranCode("배송중");
				list.set(i, (Object)product);
			}else if(product.getTranCode().equals("3")) {
				product.setProTranCode("배송완료");
				list.set(i, (Object)product);
			}
		}
		
		System.out.println("==========================\n");
		SqlSessionFactoryBean.printList(list);
		System.out.println("==========================\n");
		
		return list;		
	}

	public int updateProduct(Product product) throws Exception{
		return sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	public int getTotalCount(SearchVO search) throws Exception{
		return sqlSession.selectOne("ProductMapper.getTotalCount",search);
	}
	
	public int removeProduct(String prodName) {
		return sqlSession.delete("ProductMapper.deleteProduct", prodName);
	}
	
	public List<String> getAutocompleteList(String keyword) throws Exception{
		return sqlSession.selectList("ProductMapper.getAutoCompleteList",keyword);
	}
	
}
