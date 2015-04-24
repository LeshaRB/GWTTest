package by.gwttest.server;

import java.util.Date;

import by.gwttest.client.service.PacketService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PacketServiceImpl extends RemoteServiceServlet implements
		PacketService {

	private static final long serialVersionUID = 6113028714553683292L;

	@Override
	public Date sendPacketMSGToServer(String packet)
			throws IllegalArgumentException {
		getServletContext().log("LOG_PACKET_MSG = " + packet);
		return new Date();
	}
}
