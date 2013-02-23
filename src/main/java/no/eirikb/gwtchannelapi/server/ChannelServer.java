/**
 * Copyright (c) 2012, Eirik Brandtzæg
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
package no.eirikb.gwtchannelapi.server;

import java.lang.reflect.Method;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import no.eirikb.gwtchannelapi.client.ChannelService;
import no.eirikb.gwtchannelapi.client.DummySerializeService;
import no.eirikb.gwtchannelapi.client.Message;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import no.eirikb.gwtchannelapidemo.shared.MessageEvent;

/**
 * @author Eirik Brandtzæg <eirikb@eirikb.no>
 */
public class ChannelServer extends RemoteServiceServlet implements ChannelService {

    /**
     * Send event to all clients on a given channel. 
     * Channel is channel name here, not channel key
     *
     * @param channel
     * @param message any class of no.eirikb.gwtchannelapi.client.Message
     */
    public static void send(String channel, Message message)
            throws NoSuchMethodException, SerializationException {

        Method serviceMethod = DummySerializeService.class.getMethod("getMessage", Message.class);

        String serialized = RPC.encodeResponseForSuccess(serviceMethod, message);

        ChannelServiceFactory.getChannelService().sendMessage(new ChannelMessage(channel, serialized));
    }

    @Override
    public void sendMessage(String message) {
        // TODO: Catch properly, perhaps no-throw
        try {
            ChannelServer.send("test", new MessageEvent(message));
        } catch (Exception e) {}
    }

    @Override
    public String join() {
        String channelKey = null;
        if (channelKey == null) {
            channelKey = ChannelServiceFactory.getChannelService().createChannel("test");
        }
        return channelKey;
    }
}
