This is a wrapper of Google App Engine [Channel API](http://code.google.com/appengine/docs/java/channel/overview.html) for [Google Web Toolkit](http://code.google.com/webtoolkit)

Setup
-

Maven
--

Add this to your pom.xml:

    <dependency>
        <groupId>no.eirikb.gwtchannelapi</groupId>
        <artifactId>gwt-channel-api</artifactId>
        <version>0.1</version>
    </dependency>

Module
--

Add this to your Module.gwt.xml:

    <inherits name='no.eirikb.gwtchannelapi.GwtChannelApi' />

HTML
--

Add this to your index.html:

    <script src="/_ah/channel/jsapi"></script>

Usage
-

Create a channel on server
--

    ChannelServiceFactory.getChannelService().createChannel(CHANNELNAME);

Join a channel on client
--

    import no.eirikb.gwtchannelapi.client.Channel;
    import no.eirikb.gwtchannelapi.client.Channel.ChannelListener;

    Channel.join(channelKey, new ChannelListener() { ... });

Send/Push messages from server (serverside)
--

All classes that should be pushed MUST implement Message

    import no.eirikb.gwtchannelapi.client.Message;
        public class MessageEvent implements Message {
    }

ChannelServer.sendEvent(CHANNELNAME, new MessageEvent("Hello world"));

