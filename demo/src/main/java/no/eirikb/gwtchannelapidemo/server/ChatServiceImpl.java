/**
 * Copyright (c) 2013, Eirik Brandtzæg
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
