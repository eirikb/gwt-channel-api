/**
 * Copyright (c) 2010, Eirik Brandtzæg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  *  Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  *  Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL EIRIK BRANDTZÆG BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package no.eirikb.gwtchannelapi.client;

import no.eirikb.gwtchannelapi.shared.Event;

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
		SerializationStreamFactory ssf = GWT.create(ChannelService.class);
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
		me.@no.eirikb.gwtchannelapi.client.Channel::onMessage(Ljava/lang/String;)(s);
		}
	}-*/;
}
