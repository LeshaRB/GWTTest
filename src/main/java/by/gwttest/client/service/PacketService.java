package by.gwttest.client.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("packetService")
public interface PacketService extends RemoteService {
	Date sendPacketMSGToServer(String packet);
}
