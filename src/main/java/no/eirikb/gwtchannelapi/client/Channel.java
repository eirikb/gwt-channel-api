package no.eirikb.gwtchannelapi.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private List<ChannelListener> channelListeners;
    private String channelName;
    private final ChannelServiceAsync channelService = GWT.create(ChannelService.class);
    private String token;

    public Channel(String channelName) {
        this.channelName = channelName;
        channelListeners = new ArrayList<ChannelListener>();
    }

    private void onMessage(String encoded) throws SerializationException {
        SerializationStreamFactory ssf = GWT.create(DummySerializeService.class);
        IsSerializable message = (IsSerializable) ssf.createStreamReader(encoded).readObject();
        for (int i = 0; i < channelListeners.size(); i++) {
            channelListeners.get(i).onMessage(message);
        }
    }

    private void onOpen() {
        for (int i = 0; i < channelListeners.size(); i++) {
            channelListeners.get(i).onOpen();
        }
    }

    private void onError() {
        for (int i = 0; i < channelListeners.size(); i++) {
            channelListeners.get(i).onError();
        }
    }

    private void onClose() {
        for (int i = 0; i < channelListeners.size(); i++) {
            channelListeners.get(i).onClose();
        }
    }

    public void join() {
        channelService.join(channelName, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
            }

            @Override
            public void onSuccess(String t) {
                token = t;
                join(token);
            }
        });
    }

    public void addChannelListener(ChannelListener channelListener) {
        channelListeners.add(channelListener);
    }

    public void removeChannelListener(ChannelListener channelListener) {
        channelListeners.remove(channelListener);
    }

    private native void join(String channelKey) /*-{
        var channel = new $wnd.goog.appengine.Channel(channelKey);
        var socket = channel.open();
        var self = this;

        socket.onmessage = function(evt) {
            var data = evt.data;
            self.@no.eirikb.gwtchannelapi.client.Channel::onMessage(Ljava/lang/String;)(data);
        };

        socket.onopen = function() {
            self.@no.eirikb.gwtchannelapi.client.Channel::onOpen()();
        };

        socket.onerror = function() {
            self.@no.eirikb.gwtchannelapi.client.Channel::onError()();
        };

        socket.onclose = function() {
            self.@no.eirikb.gwtchannelapi.client.Channel::onClose()();
        };
    }-*/;

    public void send(IsSerializable message) {
        send(message, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
            }

            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }

    public void send(IsSerializable message, AsyncCallback<Void> callback) {
        channelService.onMessage(token, channelName, message, callback);
    }
}
