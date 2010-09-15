package com.goodhope.goldselling.web.view.jmesa;

import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jmesa.util.ItemUtils;
import org.jmesa.view.editor.AbstractPatternCellEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An editor to work with dates. Just send in a valid date pattern and the date
 * will be formated.
 * 
 * @since 2.0
 * @author Jeff Johnston
 */
public class CalendarCellEditor extends AbstractPatternCellEditor {
	private Logger logger = LoggerFactory.getLogger(CalendarCellEditor.class);

	public CalendarCellEditor() {
	}

	/**
	 * @param pattern
	 *            The pattern to use.
	 */
	public CalendarCellEditor(String pattern) {
		setPattern(pattern);
	}

	/**
	 * Get the formatted date value based on the pattern set.
	 */
	public Object getValue(Object item, String property, int rowcount) {
		Object itemValue = null;

		try {
			itemValue = ItemUtils.getItemValue(item, property);
			if (itemValue == null || StringUtils.isBlank(String.valueOf(itemValue))) {
				return null;
			}

			Locale locale = getWebContext().getLocale();
			itemValue = DateFormatUtils.format((Calendar) itemValue, getPattern(), locale);
		} catch (Exception e) {
			logger.warn("Could not process date editor with property " + property, e);
		}

		return itemValue;
	}
}
