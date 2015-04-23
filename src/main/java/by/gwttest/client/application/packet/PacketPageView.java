package by.gwttest.client.application.packet;

import by.gwttest.client.widgets.ValueTextBox;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

class PacketPageView extends ViewImpl implements PacketPagePresenter.MyView {
	interface Binder extends UiBinder<Widget, PacketPageView> {
	}

	@UiField
	ValueTextBox packetSize;

	@UiField
	Button sendBtn;

	@UiField
	TextArea textLog;

	@Inject
	PacketPageView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public ValueTextBox getPacketSize() {
		return packetSize;
	}

	@Override
	public Button getSendBtn() {
		return sendBtn;
	}

	@Override
	public TextArea getTextLog() {
		return textLog;
	}
}