package no.eirikb.gwtchannelapidemo.server;

import com.google.gwt.user.client.rpc.IsSerializable;
import no.eirikb.gwtchannelapi.server.ChannelServer;
import no.eirikb.gwtchannelapidemo.shared.MyMessage;

public class ChatServiceImpl extends ChannelServer {
    public void onJoin(String token, String channelName) {
        System.out.println("Join: " + token + " - " + channelName);
        send(channelName, new MyMessage("Someone joined"));
    }

    public void onMessage(String token, String channelName, IsSerializable message) {
        System.out.println("Message: " + token + " - " + channelName + " - " + message);
        send(channelName, message);
    }
}
