package no.eirikb.gwtchannelapi.client;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChannelServiceAsync {
    public void sendMessage(String message, AsyncCallback<Void> asyncCallback);

    public void connect(String channelName, AsyncCallback<Channel> asyncCallback);
}
