package no.eirikb.gwtchannelapidemo.server;

import no.eirikb.gwtchannelapi.server.ChannelServer;
import no.eirikb.gwtchannelapidemo.shared.MyFactory;
import no.eirikb.gwtchannelapidemo.shared.MyMessage;

public class ChatServiceImpl extends ChannelServer {
    public void onJoin(String token, String channelName) {
        System.out.println("Join: " + token + " - " + channelName);
    }

    public void onMessage(String token, String channelName, String message) {
        System.out.println("Message: " + token + " - " + channelName + " - " + message);

        MyMessage myMessage = factory(MyFactory.class).getMessage().as();
        myMessage.setMessage(message);

        send(channelName, myMessage);
    }
}
