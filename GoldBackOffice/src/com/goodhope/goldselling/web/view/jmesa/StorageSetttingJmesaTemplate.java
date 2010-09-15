package com.goodhope.goldselling.web.view.jmesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.facade.TableFacadeTemplate;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.view.View;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;

import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.domain.StorageList;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.ServerDao;
import com.goodhope.goldselling.web.view.jmesa.wrapper.VendorStorageSettingWrapper;

public class StorageSetttingJmesaTemplate extends TableFacadeTemplate {

	private BaseDao baseDao;
	private ServerDao serverDao;
	private Vendor vendor;
	private TableFacade tableFacade;
	private List<Server> allServers;

	public StorageSetttingJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
		this.tableFacade = tableFacade;
		this.tableFacade.setEditable(true);
	}

	public StorageSetttingJmesaTemplate(BaseDao baseDao) {
		this(TableFacadeFactory.createTableFacade("StorageSetttingJmesaTemplate", ServletActionContext.getRequest(), ServletActionContext.getResponse()));
		this.baseDao = baseDao;
	}

	@Override
	protected String[] getColumnProperties() {
		Limit limit = this.tableFacade.getLimit();
		if (limit.isExported()) {
			return new String[] { "server.country.name", "server.name", "server.faction", "price", "amount", "priceLimit", "amountLimit" };
		}
		return new String[] { "jmesaCheckBox", "id", "server.country.name", "server.name", "server.faction", "htmlPrice", "amount", "priceLimit", "server.amountLimit", "jmesaDeleteLink" };
	}

	@Override
	protected Collection<?> getItems() {
		List<VendorStorageSettingWrapper> exportList = new ArrayList<VendorStorageSettingWrapper>();
		this.vendor = this.baseDao.findById(Vendor.class, this.vendor.getId());
		Set<StorageList> storageLists = this.vendor.getStorageLists();
		Limit limit = this.tableFacade.getLimit();
		if (limit.isExported()) {
			allServers = this.serverDao.getAllServers();
			for (Server server : allServers) {
				boolean isAdd = false;
				for (StorageList storageList : storageLists) {
					if (server.getName().equals(storageList.getServer().getName()) && server.getFaction().equals(storageList.getServer().getFaction())) {
						exportList.add(new VendorStorageSettingWrapper(storageList, server));
						isAdd = true;
						break;
					}
				}
				if (!isAdd) {
					exportList.add(new VendorStorageSettingWrapper(null, server));
				}
			}
			return exportList;
		}

		return storageLists;
	}

	@Override
	protected void modifyTable(Table table) {

		Row row = table.getRow();
		Column country = row.getColumn("server.country.name");
		country.setTitle("游戏区");

		Column serverName = row.getColumn("server.name");
		serverName.setTitle("服务器");

		Column faction = row.getColumn("server.faction");
		faction.setTitle("阵营");

		Column amount = row.getColumn("amount");
		amount.setTitle("数量");

		Limit limit = this.tableFacade.getLimit();
		if (limit.isExported()) {
			Column price = row.getColumn("price");
			price.setTitle("单价");
			Column priceLimit = row.getColumn("priceLimit");
			priceLimit.setTitle("价格上限");
			Column amountLimit = row.getColumn("amountLimit");
			amountLimit.setTitle("数量下限");
		}

		if (table instanceof org.jmesa.view.html.component.HtmlTable) {
			HtmlTable htmlTable = (HtmlTable) table;
			htmlTable.getTableRenderer().setWidth("100%");

			HtmlRow htmlRow = htmlTable.getRow();
			htmlRow.setUniqueProperty("id");
			HtmlColumn id = htmlRow.getColumn("id");
			id.setEditable(false);

			HtmlColumn countryHtml = htmlRow.getColumn("server.country.name");
			countryHtml.setEditable(false);

			HtmlColumn serverNameHtml = htmlRow.getColumn("server.name");
			serverNameHtml.setEditable(false);

			HtmlColumn factionHtml = htmlRow.getColumn("server.faction");
			factionHtml.setEditable(false);

			Column htmlPrice = row.getColumn("htmlPrice");
			htmlPrice.setTitle("单价");// 为什么要写两份，因为前台显示需要对格式进行调整

			HtmlColumn priceLimitHtml = htmlRow.getColumn("priceLimit");
			priceLimitHtml.setTitle("价格上限");
			priceLimitHtml.setEditable(false);
			HtmlColumn amountLimitHtml = htmlRow.getColumn("server.amountLimit");
			amountLimitHtml.setTitle("数量下限");
			amountLimitHtml.setEditable(false);

			HtmlColumn jmesaDeleteLink = htmlRow.getColumn("jmesaDeleteLink");
			jmesaDeleteLink.setTitle("操作");
			jmesaDeleteLink.setEditable(false);
			jmesaDeleteLink.getCellRenderer().setCellEditor(new LinkCellEditor(ServletActionContext.getRequest().getContextPath() + "/afterlogin/deleteStorage", "删除", "deleteLinkClass", "id"));

			HtmlColumn checkbox = htmlRow.getColumn("jmesaCheckBox");
			checkbox.setTitle("_");
			checkbox.setEditable(false);
			checkbox.setFilterable(false);
			checkbox.setSortable(false);
			checkbox.setWidth("20px");
			checkbox.getCellRenderer().setCellEditor(new CheckBoxCellEditor("id"));
		}
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "id"), new NumericTypeFilterMatcher());
		filterMatchers.put(new MatcherKey(Long.class, "amount"), new NumericTypeFilterMatcher());
		filterMatchers.put(new MatcherKey(Long.class, "server.amountLimit"), new NumericTypeFilterMatcher());
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	protected View createView() {
		Limit limit = this.tableFacade.getLimit();
		if (limit.isExported()) {
			return null;
		}
		return new JmesaTableFootSelectAllView();
	}

	public void setTableFacade(TableFacade tableFacade) {
		this.tableFacade = tableFacade;
	}

	public TableFacade getTableFacade() {
		return tableFacade;
	}

	@Override
	protected ExportType[] getExportTypes() {
		return new ExportType[] { ExportType.JEXCEL };
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

}
