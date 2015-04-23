package by.gwttest.client.widgets;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.TextBox;

public class ValueTextBox extends TextBox {

	public ValueTextBox() {
		this.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				Character charCode = event.getCharCode();
				int unicodeCharCode = event.getUnicodeCharCode();
				if (!(Character.isDigit(charCode) || unicodeCharCode == 0)) {
					cancelKey();
				}
			}
		});
	}
}
