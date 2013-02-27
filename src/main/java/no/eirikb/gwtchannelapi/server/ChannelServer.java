package no.eirikb.gwtchannelapi.server;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import no.eirikb.gwtchannelapi.client.ChannelService;
import no.eirikb.gwtchannelapi.client.DummySerializeService;

import java.lang.reflect.Method;

public abstract class ChannelServer extends RemoteServiceServlet implements ChannelService {

    public static final void send(String channel, IsSerializable message) {
        try {
            Method serviceMethod = DummySerializeService.class.getMethod("getMessage", IsSerializable.class);

            String serialized = RPC.encodeResponseForSuccess(serviceMethod, message);

            System.out.println("Sending to channel: " + channel);
            ChannelServiceFactory.getChannelService().sendMessage(new ChannelMessage(channel, serialized));
        } catch (Exception e) {
        }
    }

    @Override
    public final String join(String channelName) {
        String token = ChannelServiceFactory.getChannelService().createChannel(channelName);
        onJoin(token, channelName);
        return token;
    }

    public abstract void onJoin(String token, String channelName);
}