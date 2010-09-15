package com.goodhope.goldselling.web.action.customerservice;

import java.util.Set;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderPicture;
import com.goodhope.goldselling.persistence.BaseDao;
import com.opensymphony.xwork2.ActionSupport;

public class ViewOrderPictureAction extends ActionSupport {

	private static final long serialVersionUID = -8464194829972656803L;
	private long orderId;
	private BaseDao baseDao;
	private Set<OrderPicture> pictures;
	private Order order;

	@Override
	public String execute() throws Exception {
		this.order = this.baseDao.findById(Order.class, orderId);
		pictures = this.order.getPictures();
		return super.execute();
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public Set<OrderPicture> getPictures() {
		return pictures;
	}

	public Order getOrder() {
		return order;
	}

	public String getOrderNumber() {
		return order.getPurchaseAttemp().getGh_transaction_id();
	}

	public String getCharactorName() {
		return order.getPurchaseAttemp().getCharacter();
	}

	public String getGoldAmount() {
		return order.getPurchaseAttemp().getGold_amount() + "";
	}

}
