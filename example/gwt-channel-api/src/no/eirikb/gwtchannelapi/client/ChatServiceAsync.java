package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ChatServiceAsync {
	void sendMessage(String input, AsyncCallback<Void> callback);

	void join(AsyncCallback<String> callback);

}
