package by.gwttest.client.utils;

import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;

public interface TimeTemplate extends SafeHtmlTemplates {
	@Template("{0} hrs : {1} min :{2} sec : {3} mil")
	SafeHtml timeTemplate(long hrs, long min, long sec, long mil);
}
