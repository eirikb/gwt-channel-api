package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;

public interface DummySerializeService extends RemoteService {
    IsSerializable getMessage(IsSerializable message);
}

interface DummySerializeServiceAsync {
    void getMessage(IsSerializable message, AsyncCallback<IsSerializable> callback);
}
