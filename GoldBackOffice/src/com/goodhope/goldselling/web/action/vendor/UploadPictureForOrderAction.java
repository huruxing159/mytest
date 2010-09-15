package com.goodhope.goldselling.web.action.vendor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderPicture;
import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.OrderRecordDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class UploadPictureForOrderAction extends ActionSupport implements UserAware, TransactionAware {

	private static final long serialVersionUID = 3618804271543801219L;
	private User user;
	private long orderId;
	private BaseDao baseDao;
	private Vendor currentVendor;
	private OrderRecordDao orderRecordDao;
	private Order order;
	private File[] upload;// The actual file
	private String[] uploadContentType; // The content type of the file
	private String[] uploadFileName; // The uploaded file name
	private List<OrderPicture> pictures = new ArrayList<OrderPicture>();

	@Override
	public String execute() throws Exception {
		if (upload == null) {
			addActionError("请选择上传的文件");
			return ActionSupport.INPUT;
		}
		for (int i = 0; i < upload.length; i++) {
			StringBuffer sb = new StringBuffer(this.uploadFileName[i]);
			int lastIndexOf = sb.lastIndexOf(".");
			String timestamp = (new Date()).getTime() + "";
			String fullFileName = System.getProperty("user.dir") + "/tomcat/webapps/GoldSelling/vendor_pictures/" + this.user.getVendor().getName() + "_" + timestamp + sb.substring(lastIndexOf);
			BufferedImage bufferedImage = ImageIO.read(this.upload[i]);
			ImageIO.write(bufferedImage, sb.substring(lastIndexOf + 1), new File(fullFileName));
			OrderPicture picture = new OrderPicture();
			picture.setLocation("vendor_pictures/" + this.user.getVendor().getName() + "_" + timestamp + sb.substring(lastIndexOf));
			pictures.add(picture);
		}

		this.order = this.baseDao.findById(Order.class, this.orderId);
		List<OrderPicture> tempOrderPicture = new ArrayList<OrderPicture>(this.order.getPictures());
		Vendor vendor = order.getCurrentVendor();
		this.currentVendor = this.user.getVendor();
		if (vendor != null && this.currentVendor != null) {
			if (vendor.getName().equals(this.currentVendor.getName())) {
				if (!OrderState.CHECKE_FAILE.equals(this.order.getState())) {
					/*
					 * 上传截图的时候，我们就认为这个时间为发货的完成时间, 客服验证失败，不能修改发货完成时间
					 */
					this.order.setDeliverTime(Calendar.getInstance());
				}
				this.order.setState(OrderState.DELIVERED_UNCHECK);
				this.baseDao.update(this.order);
				for (OrderPicture picture : pictures) {
					picture.setOrder(order);
					this.baseDao.save(picture);
				}
				List<OrderRecord> orderRecordList = this.orderRecordDao.getOrderRecordByNumberAndVendor(order.getPurchaseAttemp().getGh_transaction_id(), vendor);
				if (orderRecordList.size() > 0) {
					OrderRecord orderRecord = orderRecordList.get(0);
					orderRecord.setEvent(OrderState.DELIVERED_UNCHECK);
					this.baseDao.update(orderRecord);
				}
			}
		}
		tempOrderPicture.addAll(this.pictures);// 这样做为了让图片的顺序能够按照先后顺序，
		this.pictures = tempOrderPicture;
		return super.execute();

	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;

	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	public User getUser() {
		return user;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public List<OrderPicture> getPictures() {
		return pictures;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}

}