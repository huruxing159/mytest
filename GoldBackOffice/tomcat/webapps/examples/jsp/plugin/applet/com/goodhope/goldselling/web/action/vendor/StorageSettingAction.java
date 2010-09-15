package com.goodhope.goldselling.web.action.vendor;

import java.math.BigDecimal;
import java.util.Collection;

import org.jmesa.facade.TableFacade;
import org.jmesa.worksheet.Worksheet;
import org.jmesa.worksheet.WorksheetCallbackHandler;
import org.jmesa.worksheet.WorksheetColumn;
import org.jmesa.worksheet.WorksheetRow;

import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.StorageList;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.OrderDao;
import com.goodhope.goldselling.persistence.ServerDao;
import com.goodhope.goldselling.web.UserAware;
import com.goodhope.goldselling.web.view.jmesa.StorageSetttingJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class StorageSettingAction extends ActionSupport implements UserAware {

	private static final long serialVersionUID = -148907313343598860L;
	private User user;
	private BaseDao baseDao;
	private OrderDao orderDao;
	private int newOrderSize;
	private String jmesaViewCode;
	private ServerDao serverDao;
	private StorageSetttingJmesaTemplate storageSetttingJmesaTemplate;

	@Override
	public String execute() throws Exception {
		newOrderSize = orderDao.getAllNewOrdersByVendor(this.user.getVendor(), OrderState.ASSIGNED_UNACCEPT, OrderState.ACCEPTED_UNDELIVER).size();
		storageSetttingJmesaTemplate = new StorageSetttingJmesaTemplate(this.baseDao);
		storageSetttingJmesaTemplate.setServerDao(serverDao);
		this.storageSetttingJmesaTemplate.setVendor(this.user.getVendor());
		saveWorksheet(this.storageSetttingJmesaTemplate.getTableFacade());
		this.jmesaViewCode = this.storageSetttingJmesaTemplate.render();
		return super.execute();
	}

	private void saveWorksheet(TableFacade tableFacade) {
		Worksheet worksheet = tableFacade.getWorksheet();
		if (!worksheet.isSaving() || !worksheet.hasChanges()) {
			return;
		}
		worksheet.processRows(new WorksheetCallbackHandler() {
			public void process(WorksheetRow worksheetRow) {
				Collection<WorksheetColumn> columns = worksheetRow.getColumns();
				String id = worksheetRow.getUniqueProperty().getValue();
				StorageList storageList = baseDao.findById(StorageList.class, Long.parseLong(id));
				for (WorksheetColumn worksheetColumn : columns) {
					if (worksheetColumn.getProperty().equals("htmlPrice")) {
						String changedValue = worksheetColumn.getChangedValue();
						validateColumn(worksheetColumn, changedValue);
						if (worksheetColumn.hasError()) {
							continue;
						}
						/*
						 * 修改的价格不能超过价格上限
						 */
						BigDecimal changedPrice = new BigDecimal(changedValue);
						if (changedPrice.compareTo(storageList.getServer().getPriceLimit()) < 0) {
							storageList.setPrice(new BigDecimal(changedValue));
						} else {
							addActionError("修改的价格(" + changedPrice.toPlainString() + ")不能超过价格上限(" + storageList.getServer().getPriceLimit().toPlainString() + ")");
						}

					}
					if (worksheetColumn.getProperty().equals("amount")) {
						String changedValue = worksheetColumn.getChangedValue();
						validateColumn(worksheetColumn, changedValue);
						if (worksheetColumn.hasError()) {
							continue;
						}

						storageList.setAmount(Long.parseLong(changedValue));
					}
				}
				baseDao.update(storageList);
			}

			private void validateColumn(WorksheetColumn worksheetColumn, String changedValue) {
				try {
					BigDecimal change = new BigDecimal(changedValue);
					if (change.compareTo(new BigDecimal(0)) < 0) {
						addActionError("请输入正数");
						throw new Exception();
					}
					if (change.compareTo(new BigDecimal(Integer.MAX_VALUE)) >= 0) {
						addActionError("输入的值过大，请输入合理的正数");
						throw new Exception();
					}
					worksheetColumn.removeError();
				} catch (Exception e) {
					worksheetColumn.setError("error");
				}
			}
		});

	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;

	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public String getJmesaViewCode() {
		return jmesaViewCode;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public int getNewOrderSize() {
		return newOrderSize;
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

}
