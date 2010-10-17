package no.eirikb.gwtchannelapi.client;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("metaService")
public interface MetaService extends RemoteService {
        IsSerializable getSerializable(IsSerializable isSerializable);

        void publish(String channelId, IsSerializable message);

        void publish(String channelId, IsSerializable message, String clientId);
}