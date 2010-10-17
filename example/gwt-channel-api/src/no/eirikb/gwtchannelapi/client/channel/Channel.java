package no.eirikb.gwtchannelapi.client.channel;

import no.eirikb.gwtchannelapi.client.ChatService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;

public class Channel {
	public interface ChannelListener {
		void onMessage(Event event);
	}

	private Channel(ChannelListener channelListener) {
		this.channelListener = channelListener;
	}

	private ChannelListener channelListener;

	private void onMessage(String encoded) {
		SerializationStreamFactory ssf = GWT.create(ChatService.class);
		try {
			Event event = (Event) ssf.createStreamReader(encoded).readObject();
			if (channelListener != null) {
				channelListener.onMessage(event);
			}
		} catch (SerializationException e) {
			e.printStackTrace();
		}
	}

	public static void join(String channelKey, ChannelListener channelListener) {
		Channel channel = new Channel(channelListener);
		channel.join(channelKey);
	}

	private native void join(String channelKey) /*-{
		var c = new $wnd.goog.appengine.Channel(channelKey);
		var socket = c.open();
		var me = this;
		socket.onmessage = function(evt) {
		var s = evt.data;
		me.@no.eirikb.gwtchannelapi.client.channel.Channel::onMessage(Ljava/lang/String;)(s);
		}
	}-*/;
}
