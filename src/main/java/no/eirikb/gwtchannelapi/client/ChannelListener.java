package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;

public interface ChannelListener extends RemoteService {

    void onMessage(IsSerializable message);

    void onOpen();

    void onError();

    void onClose();
}
