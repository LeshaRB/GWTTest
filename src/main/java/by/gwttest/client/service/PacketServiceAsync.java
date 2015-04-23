package by.gwttest.client.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PacketServiceAsync {
	void sendPacketMSGToServer(String packet, AsyncCallback<Date> callback);
}
