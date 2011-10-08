The point of this project is to provide working examples and a tiny library for the GAE Channel API in GWT with as little code as possible

Usage
-

Your module
--

    <inherits name='no.eirikb.gwtchannelapi.GwtChannelApi' />

HTML
--
Add this to your HTML:

    <script src="/_ah/channel/jsapi"></script>

Create a channel (serverside)
--

    ChannelServiceFactory.getChannelService().createChannel(CHANNELNAME);

Join a channel (clientside)
--

    import no.eirikb.gwtchannelapi.client.Channel;
    import no.eirikb.gwtchannelapi.client.Channel.ChannelListener;

    Channel.join(channelKey, new ChannelListener() { ... });

Send/Push messages from server (serverside)
All classes that should be pushed MUST implement Message

    import no.eirikb.gwtchannelapi.client.Message;
    public class MessageEvent implements Message {
    }

ChannelServer.sendEvent(CHANNELNAME, new MessageEvent("Hello world"));

