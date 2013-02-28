package no.eirikb.gwtchannelapi.server;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import no.eirikb.gwtchannelapi.client.ChannelService;

public abstract class ChannelServer extends RemoteServiceServlet implements ChannelService {

    public static <F extends AutoBeanFactory> F factory(Class<F> clazz) { return AutoBeanFactorySource.create(clazz); }

    protected static void send(String channel, Object o) {
        AutoBean bean = AutoBeanUtils.getAutoBean(o);
        String serialized = AutoBeanCodex.encode(bean).getPayload();

        ChannelServiceFactory.getChannelService().sendMessage(new ChannelMessage(channel, serialized));
    }

    @Override
    public final String join(String channelName) {
        String token = ChannelServiceFactory.getChannelService().createChannel(channelName);
        onJoin(token, channelName);
        return token;
    }

    protected abstract void onJoin(String token, String channelName);
}