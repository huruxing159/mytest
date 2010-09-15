package com.goodhope.goldselling.web.view.jmesa;

import org.jmesa.util.ItemUtils;
import org.jmesa.view.editor.AbstractCellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.editor.HtmlCellEditor;

public class LinkCellEditor extends AbstractCellEditor {

	private String actionName;
	private String[] parameters;
	private String operation;
	private String htmlClass;

	public LinkCellEditor(String actionName, String operation, String htmlClass, String... parameters) {
		super();
		this.actionName = actionName;
		this.operation = operation;
		this.htmlClass = htmlClass;
		this.parameters = parameters;
	}

	@Override
	public Object getValue(Object item, String property, int rowcount) {
		Object value = new HtmlCellEditor().getValue(item, property, rowcount);
		HtmlBuilder html = new HtmlBuilder();
		html.a().href().quote().append(constructLink(item)).quote().append(" class=" + htmlClass).close();
		if (operation == null) {
			html.append(value);
		} else {
			html.append(operation);
		}
		html.aEnd();
		return html.toString();
	}

	private String constructLink(Object item) {
		StringBuilder sb = new StringBuilder(actionName + "?");
		for (int i = 0; i < parameters.length; i++) {
			sb.append(parameters[i]).append("=").append(ItemUtils.getItemValue(item, parameters[i]));
			if (i != parameters.length - 1) {
				sb.append("&");
			}
		}
		return sb.toString();

	}

}
