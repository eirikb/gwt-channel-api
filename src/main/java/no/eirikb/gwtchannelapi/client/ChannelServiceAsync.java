package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;

public interface ChannelServiceAsync {
    public void join(String channelName, AsyncCallback<String> asyncCallback);

    public void send(String channel, IsSerializable message, AsyncCallback<Void> asyncCallback);
}
