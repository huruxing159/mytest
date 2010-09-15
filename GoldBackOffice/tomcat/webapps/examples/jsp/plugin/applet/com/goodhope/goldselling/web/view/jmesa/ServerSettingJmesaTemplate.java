package com.goodhope.goldselling.web.view.jmesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import com.goodhope.goldselling.persistence.ServerDao;
import com.goodhope.goldselling.web.view.jmesa.wrapper.ServerSettingWrapper;

public class ServerSettingJmesaTemplate extends TableFacadeTemplate {

	private TableFacade tableFacade;
	private ServerDao serverDao;

	public ServerSettingJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
		this.tableFacade = tableFacade;
	}

	public ServerSettingJmesaTemplate(ServerDao serverDao) {
		this(TableFacadeFactory.createTableFacade("ServerSettingJmesaTemplate", ServletActionContext.getRequest(), ServletActionContext.getResponse()));
		this.serverDao = serverDao;
		this.tableFacade.setEditable(true);
	}

	@Override
	protected ExportType[] getExportTypes() {
		return new ExportType[] { ExportType.JEXCEL };
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "id"), new NumericTypeFilterMatcher());
		filterMatchers.put(new MatcherKey(Long.class, "goldAmount"), new NumericTypeFilterMatcher());
		filterMatchers.put(new MatcherKey(Long.class, "server.amountLimit"), new NumericTypeFilterMatcher());
	}

	@Override
	protected String[] getColumnProperties() {
		Limit limit = this.tableFacade.getLimit();
		if (limit.isExported()) {
			return new String[] { "server.country.name", "server.name", "server.faction", "current_unit_price", "priceLimit", "server.amountLimit" };
		}
		return new String[] { "jmesaCheckBox", "id", "server.country.name", "server.name", "server.faction", "current_unit_price", "goldAmount", "priceLimit", "server.amountLimit", "server.updated_at", "jmesaDelete" };
	}

	@Override
	protected Collection<?> getItems() {
		List<ServerSettingWrapper> serverList = new ArrayList<ServerSettingWrapper>();
		for (Server server : this.serverDao.getAllServers()) {
			serverList.add(new ServerSettingWrapper(server));
		}
		return serverList;
	}

	@Override
	protected void modifyTable(Table table) {

		Row row = table.getRow();
		Column country = row.getColumn("server.country.name");
		country.setTitle("游戏区");

		Column name = row.getColumn("server.name");
		name.setTitle("服务器");

		Column faction = row.getColumn("server.faction");
		faction.setTitle("阵营");

		Column current_unit_price = row.getColumn("current_unit_price");
		current_unit_price.setTitle("单价");

		Column priceLimit = row.getColumn("priceLimit");
		priceLimit.setTitle("价格上限");

		Column amountLimit = row.getColumn("server.amountLimit");
		amountLimit.setTitle("数量下限");

		if (table instanceof org.jmesa.view.html.component.HtmlTable) {
			HtmlTable htmlTable = (HtmlTable) table;
			htmlTable.getTableRenderer().setWidth("100%");

			HtmlRow htmlRow = htmlTable.getRow();
			htmlRow.setUniqueProperty("id");
			HtmlColumn id = htmlRow.getColumn("id");
			id.setEditable(false);

			HtmlColumn countryHtml = htmlRow.getColumn("server.country.name");
			countryHtml.setEditable(false);

			HtmlColumn nameHtml = htmlRow.getColumn("server.name");
			nameHtml.setEditable(false);

			HtmlColumn factionHtml = htmlRow.getColumn("server.faction");
			factionHtml.setEditable(false);

			HtmlColumn goldAmount = htmlRow.getColumn("goldAmount");
			goldAmount.setTitle("数量");
			goldAmount.setEditable(false);

			HtmlColumn updatedAtHtml = htmlRow.getColumn("server.updated_at");
			updatedAtHtml.setTitle("更新时间");
			updatedAtHtml.getCellRenderer().setCellEditor(new CalendarCellEditor("yyyy-MM-dd HH:mm:ss"));
			updatedAtHtml.setEditable(false);

			HtmlColumn deleteLink = htmlRow.getColumn("jmesaDelete");
			deleteLink.setTitle("操作");
			deleteLink.getCellRenderer().setCellEditor(new LinkCellEditor(ServletActionContext.getRequest().getContextPath() + "/afterlogin/deleteServer", "删除", "deleteLinkClass", "id"));
			deleteLink.setEditable(false);

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
	protected View createView() {
		Limit limit = this.tableFacade.getLimit();
		if (limit.isExported()) {
			return null;
		}
		return new JmesaTableFootSelectAllView();
	}

	public TableFacade getTableFacade() {
		return this.tableFacade;
	}

}
