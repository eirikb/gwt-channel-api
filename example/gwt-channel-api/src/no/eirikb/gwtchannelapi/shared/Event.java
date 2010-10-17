package no.eirikb.gwtchannelapi.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class Event implements IsSerializable {
	public abstract void execute();
}
