package no.eirikb.gwtchannelapi.client.channel;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class Event implements IsSerializable {
	public abstract void execute();
}
