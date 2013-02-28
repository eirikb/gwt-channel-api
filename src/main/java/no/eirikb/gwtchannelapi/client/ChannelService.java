package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("channel")
public interface ChannelService extends RemoteService {
    public String join(String channelName);

    public void onMessage(String token, String channel, String message);
}
