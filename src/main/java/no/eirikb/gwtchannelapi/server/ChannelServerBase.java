package no.eirikb.gwtchannelapi.server;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.ChannelService;
import no.eirikb.gwtchannelapi.client.DummySerializeService;

import java.lang.reflect.Method;


public abstract class ChannelServerBase extends RemoteServiceServlet implements ChannelService {


    /*
    @Override
    public void send(String channel, String message) {
                                         try {
        Method serviceMethod = DummySerializeService.class.getMethod("getMessage", IsSerializable.class);

        String serialized = RPC.encodeResponseForSuccess(serviceMethod, message);

        ChannelServiceFactory.getChannelService().sendMessage(new ChannelMessage(channel, serialized));
                                         } catch (Exception e){}
    }
    */

    @Override
    public String join(String channelName) {
        String token =  ChannelServiceFactory.getChannelService().createChannel(channelName);
        onJoin(channelName);
        return token;
    }

    @Override
    public void send(String channel, String message) {
        ChannelServiceFactory.getChannelService().sendMessage(new ChannelMessage(channel, message));
    }

    abstract void onJoin(String wat);
}