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
package no.eirikb.gwtchannelapidemo.client;

import no.eirikb.gwtchannelapi.client.*;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtChannelApiDemo implements EntryPoint {

    private final ChannelServiceAsync channelService = GWT.create(ChannelService.class);

    private TextArea chat;
    private TextBox messageBox;
    private Button sendButton;
    private String channelKey;

    public void onModuleLoad() {
        chat = new TextArea();
        chat.setWidth("400px");
        chat.setHeight("300px");
        messageBox = new TextBox();
        messageBox.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == 13) {
                    sendButton.click();
                }
            }
        });

        sendButton = new Button("Send", new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                String message = messageBox.getText();
                if (!message.isEmpty()) {
                    messageBox.setText("");
                    append("Sending message: " + message);
                    channelService.sendMessage(message, new AsyncCallback<Void>() {

                        @Override
                        public void onSuccess(Void result) {
                        }

                    @Override
                        public void onFailure(Throwable caught) {
                            append("Failure: " + caught);
                        }
                    });
                }
            }
        });

        append("Logging on...");
        channelService.join(new AsyncCallback<String>() {

            @Override
            public void onSuccess(String result) {
                channelKey = result;
                append("Channel key: " + channelKey);
                Channel channel = new Channel(channelKey);
                channel.addChannelListener(new ChannelListener() {

                    @Override
                    public void onReceive(Message message) {
                        append(message.toString());

                    }

                    @Override
                    public void onOpen() {
                        append("Connection open!");
                    }

                    @Override
                    public void onError() {
                        append("Error!");
                    }

                    @Override
                    public void onClose() {
                        append("Close!");
                    }
                });
                channel.join();
            }

        @Override
            public void onFailure(Throwable caught) {

            }
        });

        VerticalPanel vp = new VerticalPanel();
        vp.add(chat);
        HorizontalPanel hp = new HorizontalPanel();
        hp.add(messageBox);
        hp.add(sendButton);
        vp.add(hp);
        RootPanel.get().add(vp);
    }

    public void append(String line) {
        String text = chat.getText();
        chat.setText(text.length() > 0 ? text + '\n' + line : line);
    }
}
