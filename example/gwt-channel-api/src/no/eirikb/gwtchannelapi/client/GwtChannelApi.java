package no.eirikb.gwtchannelapi.client;

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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtChannelApi implements EntryPoint {

	private final ChatServiceAsync chatService = GWT.create(ChatService.class);

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
					chatService.sendMessage(message, new AsyncCallback<Void>() {

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
		chatService.join(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				channelKey = result;
				append("Channel key: " + channelKey);
				join(channelKey);
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

	private void append(String line) {
		String text = chat.getText();
		chat.setText(text.length() > 0 ? text + '\n' + line : line);
	}
	
	native void join(String channelKey) /*-{
		var c = new $wnd.goog.appengine.Channel(channelKey);
		var socket = c.open();
		var me = this;
		socket.onmessage = function(evt) {
			var s = evt.data;
			me.@no.eirikb.gwtchannelapi.client.GwtChannelApi::append(Ljava/lang/String;)(s);
		}
	}-*/;
}
