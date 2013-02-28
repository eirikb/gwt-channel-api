package no.eirikb.gwtchannelapidemo.server;

import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import no.eirikb.gwtchannelapi.server.ChannelServer;
import no.eirikb.gwtchannelapidemo.shared.MyFactory;
import no.eirikb.gwtchannelapidemo.shared.MyMessage;

public class ChatServiceImpl extends ChannelServer {
    public void onJoin(String token, String channelName) {
        System.out.println("Join: " + token + " - " + channelName);
    }

    public void onMessage(String token, String channelName, String message) {
        System.out.println("Message: " + token + " - " + channelName + " - " + message);

        MyFactory myFactory = AutoBeanFactorySource.create(MyFactory.class);
        MyMessage myMessage = myFactory.getMessage().as();
        myMessage.setMessage(message);

        send(channelName, myMessage);
    }
}
