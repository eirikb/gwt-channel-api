package no.eirikb.gwtchannelapi.server;

public class ChannelServer extends ChannelServerBase {

    public void onJoin(String wat) {
        System.out.println("CONNECT! " + wat);
    }
}
