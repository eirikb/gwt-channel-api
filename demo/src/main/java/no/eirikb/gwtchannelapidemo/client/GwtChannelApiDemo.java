package no.eirikb.gwtchannelapidemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.*;
import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.ChannelListener;

public class GwtChannelApiDemo implements EntryPoint {

    private TextArea chat;
    private TextBox messageBox;
    private Button sendButton;
    private Channel channel;

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
                if (channel == null) {
                    append("Not connected");
                    return;
                }

                String message = messageBox.getText();
                if (message.isEmpty()) return;

                messageBox.setText("");
                append("Sending message: " + message);
                channel.send(message);
            }
        });

        append("Joining...");

        channel = new Channel("test");
        channel.addChannelListener(new ChannelListener() {

            @Override
            public void onMessage(String message) {
                append("Message: " + message);

            }

            @Override
            public void onOpen() {
                append("Joined!");
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
