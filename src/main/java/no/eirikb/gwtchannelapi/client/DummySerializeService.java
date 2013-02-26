package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("channel_service")
public interface DummySerializeService extends RemoteService {
    IsSerializable getMessage(IsSerializable message);
}

 interface DummySerializeServiceAsync {
    void getMessage(IsSerializable message, AsyncCallback<IsSerializable> callback);
}
