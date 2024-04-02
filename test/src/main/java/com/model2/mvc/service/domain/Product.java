package com.model2.mvc.service.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public class Product {
	
	private String fileName;
	private String manuDate; // 
	private int price; //
	private String prodDetail; //
	private String prodName; // 
	private int prodNo; // 
	private Date regDate; //
	private String proTranCode; //
	
	
	private String tranCode;
	private int tranNo;
	
	private List<String> fileNames;
	private List<MultipartFile> file;
	
	
	
	public Product(){
	}
	
	
	
	public Product(int price, String prodDetail, String prodName) {
		super();
		this.price = price;
		this.prodDetail = prodDetail;
		this.prodName = prodName;
	}



	public String getProTranCode() {
		return proTranCode;
	}
	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		
		if(this.fileName == null || this.fileName.equals("") ) {
			this.fileName = fileName;
		}else {
			this.fileName += ("," + fileName);
		}
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate.replaceAll("-", "");
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getTranCode() {
		return tranCode;
	}



	public void setTranCode(String tranCode) {
		this.tranCode = tranCode.trim();
	}



	public int getTranNo() {
		return tranNo;
	}



	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}



	public List<MultipartFile> getFile() {
		return file;
	}



	public void setFile(List<MultipartFile> files) {
		this.file = files;
		
		if(files != null) {
			
			this.fileNames = new ArrayList<String>();
			
			for(MultipartFile file : files) {
				System.out.println(file.getOriginalFilename());
				System.out.println(files.size());
				
				UUID uuid = UUID.randomUUID();
				System.out.println("domain uuid :: " + uuid.toString());
				System.out.println("uuid split :: " + uuid.toString().split("-")[0]);
				System.out.println("fileNames::" + fileNames.toString());
				fileNames.add(uuid.toString().split("-")[0].toString()); //파일 명을 unique 하게 바꾼 다면..?
				
				System.out.println("fileNames::" + fileNames.toString());
				
			}
			System.out.println("Product set UUID FileNames::" + fileNames.toString());
			
			
			
		}
	}



	public List<String> getFileNames() {
		return fileNames;
	}
	
	public String getFileNames(int idx) {
		return fileNames.get(idx);
	}


	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}


	@Override
	public String toString() {
		return "Product [fileName=" + fileName + ", manuDate=" + manuDate + ", price=" + price + ", prodDetail="
				+ prodDetail + ", prodName=" + prodName + ", prodNo=" + prodNo + ", regDate=" + regDate
				+ ", proTranCode=" + proTranCode + ", tranCode=" + tranCode + ", tranNo=" + tranNo + "]";
	}



	

	
}