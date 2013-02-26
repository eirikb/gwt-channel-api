package no.eirikb.gwtchannelapidemo.server;

import com.google.gwt.user.client.rpc.IsSerializable;
import no.eirikb.gwtchannelapi.server.ChannelServer;

public class ChatServiceImpl extends ChannelServer {
    public void onJoin(String token, String channel) {
        System.out.println("Join: " + token + " - " + channel);
    }

    public void onMessage(String token, String channelName, IsSerializable message) {
        System.out.println("Message: " + token + " - " + channelName + " - " + message);
    }
}
