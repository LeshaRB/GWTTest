package by.gwttest.server;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import by.gwttest.client.service.PacketService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PacketServiceImpl extends RemoteServiceServlet implements
		PacketService {

	private static final long serialVersionUID = 6113028714553683292L;

	@Override
	public Date sendPacketMSGToServer(String packet)
			throws IllegalArgumentException {
		// String serverInfo = getServletContext().getServerInfo();
		// String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Date();
	}
}
