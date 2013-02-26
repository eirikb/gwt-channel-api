package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.io.Serializable;

@RemoteServiceRelativePath("chat")
public interface ChannelService extends RemoteService {
    public String join(String channelName);

    public void send(String channel, IsSerializable message);
}
