package by.gwttest.client.application.packet;

import java.util.Date;

import by.gwttest.client.application.ApplicationPresenter;
import by.gwttest.client.place.NameTokens;
import by.gwttest.client.service.PacketService;
import by.gwttest.client.service.PacketServiceAsync;
import by.gwttest.client.utils.OperationTime;
import by.gwttest.client.utils.RandomString;
import by.gwttest.client.widgets.ValueTextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class PacketPagePresenter extends
		Presenter<PacketPagePresenter.MyView, PacketPagePresenter.MyProxy> {

	private final PacketServiceAsync packetService = GWT
			.create(PacketService.class);

	interface MyView extends View {
		public ValueTextBox getPacketSize();

		public Button getSendBtn();

		public TextArea getTextLog();
	}

	@ProxyStandard
	@NameToken(NameTokens.packet)
	interface MyProxy extends ProxyPlace<PacketPagePresenter> {
	}

	private final String btnStart = "Start";
	private final String btnStop = "Stop";

	private RandomString randomString = RandomString.getInstance();
	private OperationTime operationTime = OperationTime.getInstance();

	private boolean statusReadyToSend;

	private void init() {
		getView().getSendBtn().setText(btnStart);
		getView().getPacketSize().setText("64");
		getView().getTextLog().setEnabled(false);
		statusReadyToSend = false;
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
				if (checkPacketSize(Integer.parseInt(getView().getPacketSize()
						.getText()))) {
					statusReadyToSend = !statusReadyToSend;
					if (!statusReadyToSend) {
						getView().getSendBtn().setText(btnStart);
						getView().getPacketSize().setEnabled(true);
					} else {
						getView().getSendBtn().setText(btnStop);
						getView().getPacketSize().setEnabled(false);
						getView().getTextLog().setText("");
						randomString.setLength(Integer.parseInt(getView()
								.getPacketSize().getText()));
						startSendPacket();
					}
				} else {
					Window.alert("Packet size must be between 16 and 1024!");
				}
			}
		};
	}

	private void startSendPacket() {
		Timer timer = new Timer() {
			@Override
			public void run() {
				operationTime.startTimer();
				String str = randomString.nextString();
				final String textLog = getView().getTextLog().getText();
				packetService.sendPacketMSGToServer(str,
						new AsyncCallback<Date>() {
							@Override
							public void onFailure(Throwable caught) {
								StringBuilder sb = new StringBuilder(textLog)
										.append("\n ERROR");
								getView().getTextLog().setText(sb.toString());
							}

							@Override
							public void onSuccess(Date date) {
								operationTime.stopTimer();
								StringBuilder sb = new StringBuilder(textLog)
										.append((!textLog.isEmpty()) ? "\n"
												: "")
										.append(DateTimeFormat.getFormat(
												"yyyy-MM-dd HH:mm:ss").format(
												date)).append(" time = ")
										.append(operationTime.getTime());
								getView().getTextLog().setText(sb.toString());

							}
						});

				if (!statusReadyToSend) {
					cancel();
				}
			}

		};
		timer.scheduleRepeating(1000);
	}

	private boolean checkPacketSize(Integer size) {
		return (size >= 16 && size <= 1024) ? true : false;
	}

	@Override
	public void onBind() {
		getView().getSendBtn().addClickHandler(btnStartStopHandler());
	}
}