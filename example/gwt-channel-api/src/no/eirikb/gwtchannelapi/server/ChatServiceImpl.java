package no.eirikb.gwtchannelapi.server;

import no.eirikb.gwtchannelapi.client.ChatService;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ChatServiceImpl extends RemoteServiceServlet implements
		ChatService {

	private final String CHANNELNAME = "test";
	private static String channelKey;

	@Override
	public String join() {
		if (channelKey == null) {
			channelKey = ChannelServiceFactory.getChannelService()
					.createChannel(CHANNELNAME);
		}
		return channelKey;
	}

	@Override
	public void sendMessage(String message) {
		ChannelServiceFactory.getChannelService().sendMessage(
				new ChannelMessage(CHANNELNAME, message));

	}
}
