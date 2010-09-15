package com.goodhope.goldselling.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {

	private long id;
	private String state;
	private PurchaseAttemp purchaseAttemp;
	private Vendor currentVendor;
	private BigDecimal vendorUnitPrice;
	private Calendar createTime;
	private User customerService;
	private Calendar balanceTime;// 订单的结算时间
	private Calendar acceptTime;// 订单的接受时间
	private Calendar deliverTime;// 订单的发货时间

	private Set<OrderRecord> orderRecords = new HashSet<OrderRecord>();
	private Set<OrderPicture> pictures = new HashSet<OrderPicture>();
	private List<FailReason> failReason = new ArrayList<FailReason>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PurchaseAttemp getPurchaseAttemp() {
		return purchaseAttemp;
	}

	public void setPurchaseAttemp(PurchaseAttemp purchaseAttemp) {
		this.purchaseAttemp = purchaseAttemp;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public void setOrderRecords(Set<OrderRecord> orderRecords) {
		this.orderRecords = orderRecords;
	}

	public Set<OrderRecord> getOrderRecords() {
		return orderRecords;
	}

	public void setCurrentVendor(Vendor currentVendor) {
		this.currentVendor = currentVendor;
	}

	public Vendor getCurrentVendor() {
		return currentVendor;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public String getJmesaOperation() {
		return "";
	}

	public void setPictures(Set<OrderPicture> pictures) {
		this.pictures = pictures;
	}

	public Set<OrderPicture> getPictures() {
		return pictures;
	}

	public void setCustomerService(User customerService) {
		this.customerService = customerService;
	}

	public User getCustomerService() {
		return customerService;
	}

	public void setBalanceTime(Calendar balanceTime) {
		this.balanceTime = balanceTime;
	}

	public Calendar getBalanceTime() {
		return balanceTime;
	}

	public void setAcceptTime(Calendar acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Calendar getAcceptTime() {
		return acceptTime;
	}

	public void setDeliverTime(Calendar deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Calendar getDeliverTime() {
		return deliverTime;
	}

	public void setVendorUnitPrice(BigDecimal vendorUnitPrice) {
		this.vendorUnitPrice = vendorUnitPrice;
	}

	public BigDecimal getVendorUnitPrice() {
		return vendorUnitPrice;
	}

	public void setFailReason(List<FailReason> failReason) {
		this.failReason = failReason;
	}

	public List<FailReason> getFailReason() {
		return failReason;
	}

}
