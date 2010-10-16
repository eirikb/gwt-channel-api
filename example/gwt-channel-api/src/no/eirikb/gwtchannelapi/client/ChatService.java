package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("chat")
public interface ChatService extends RemoteService {
	void sendMessage(String message);

	String join();
}
