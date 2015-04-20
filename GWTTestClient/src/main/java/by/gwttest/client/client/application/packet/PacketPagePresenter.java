package by.gwttest.client.client.application.packet;

import javax.inject.Inject;

import by.gwttest.client.client.application.ApplicationPresenter;
import by.gwttest.client.client.place.NameTokens;
import by.gwttest.client.client.widgets.ValueTextBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class PacketPagePresenter extends
		Presenter<PacketPagePresenter.MyView, PacketPagePresenter.MyProxy> {
	interface MyView extends View {
		public TextBox getServerURL();

		public ValueTextBox getPacketSize();

		public Button getSendBtn();

		public TextArea getTextLog();
	}

	private final String btnStart = "Start";
	private final String btnStop = "Stop";

	private boolean statusSend;

	@ProxyStandard
	@NameToken(NameTokens.packet)
	interface MyProxy extends ProxyPlace<PacketPagePresenter> {
	}

	private void init() {
		getView().getSendBtn().setText(btnStart);
		getView().getSendBtn().addClickHandler(btnStartStopHandler());
		getView().getPacketSize().setText("16");
		getView().getTextLog().setEnabled(false);
		statusSend = false;
	}

	@Inject
	PacketPagePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
		init();
	}

	private ClickHandler btnStartStopHandler() {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (statusSend) {
					statusSend = !statusSend;
					getView().getSendBtn().setText(btnStart);
					getView().getPacketSize().setEnabled(true);
				} else if (checkPacketSize(Integer.parseInt(getView()
						.getPacketSize().getText()))) {
					statusSend = !statusSend;
					getView().getSendBtn().setText(btnStop);
					getView().getPacketSize().setEnabled(false);
					getView().getTextLog().setText("");
				} else {
					Window.alert("Packet size must be between 16 and 1024!");
				}
			}
		};
	}

	private boolean checkPacketSize(Integer size) {
		return (size >= 16 && size <= 1024) ? true : false;
	}
}