package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;

public interface MetaServiceAsync {

	void getSerializable(IsSerializable isSerializable,
			AsyncCallback<IsSerializable> callback);

	void publish(String channelId, IsSerializable message,
			AsyncCallback<Void> callback);

	void publish(String channelId, IsSerializable message, String clientId,
			AsyncCallback<Void> callback);

}
