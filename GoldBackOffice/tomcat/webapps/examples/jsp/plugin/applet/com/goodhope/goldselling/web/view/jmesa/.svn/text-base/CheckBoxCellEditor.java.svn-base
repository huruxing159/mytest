package com.goodhope.goldselling.web.view.jmesa;

import org.apache.log4j.Logger;
import org.jmesa.view.editor.AbstractCellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.editor.HtmlCellEditor;

public class CheckBoxCellEditor extends AbstractCellEditor {

	private String property;
	private static final Logger LOG = Logger.getLogger(CheckBoxCellEditor.class);

	public CheckBoxCellEditor(String property) {
		super();
		this.property = property;

	}

	@Override
	public Object getValue(Object item, String property, int rowcount) {
		Object value = new HtmlCellEditor().getValue(item, this.property, rowcount);
		HtmlBuilder html = new HtmlBuilder();
		html.input().type("checkbox").append(" value=" + value).append(" name='id' ").close();
		LOG.debug(html.toString());
		return html.toString();
	}

}
