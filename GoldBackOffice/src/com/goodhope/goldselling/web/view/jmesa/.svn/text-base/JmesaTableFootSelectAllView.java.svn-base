package com.goodhope.goldselling.web.view.jmesa;

import org.jmesa.view.html.AbstractHtmlView;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.HtmlSnippets;

/*
 * 这个view主要功能是自定义表格的尾部，添加一个‘全选’的checkbox
 * 
 */
public class JmesaTableFootSelectAllView extends AbstractHtmlView {

	private String operationHtml = "<div id='deleteAllDiv'><a href='#' id='deleteAllLink'>删除</a></div>";

	public JmesaTableFootSelectAllView() {
		super();
	}

	public JmesaTableFootSelectAllView(String operationHtml) {
		this.operationHtml = operationHtml;
	}

	@Override
	public Object render() {
		HtmlSnippets snippets = getHtmlSnippets();
		HtmlBuilder html = new HtmlBuilder();
		html.append(snippets.themeStart());
		html.append(snippets.tableStart());
		html.append(snippets.theadStart());
		html.append(snippets.toolbar());
		html.append(snippets.filter());
		html.append(snippets.header());
		html.append(snippets.theadEnd());
		html.append(snippets.tbodyStart());
		html.append(snippets.body());
		html.append(snippets.tbodyEnd());
		html.append(snippets.footer());
		html.append("<tbody><tr class='statusBar'><td><input type='checkbox' name='checkboxSelectAll' /></td><td>全选</td><td colspan='25'>" + operationHtml + "</td> </tr></tbody>");
		html.append(snippets.statusBar());
		html.append(snippets.tableEnd());
		html.append(snippets.themeEnd());
		html.append(snippets.initJavascriptLimit());
		return html.toString();
	}
}
