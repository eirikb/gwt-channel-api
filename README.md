Demo
-

To run the demo do this:

    mvn install
    cd demo
    mvn install gwt:run

Usage
-

You will find the jar-files under the lib-folder if you don't use maven.  
With maven use this:

    <dependency>
        <groupId>no.eirikb.gwtchannelapi</groupId>
        <artifactId>gwt-channel-api</artifactId>
        <version>0.1-SNAPSHOT</version>
    </dependency>

**Your module**

    <inherits name='no.eirikb.gwtchannelapi.GwtChannelApi' />

**HTML**

Add this to your HTML:

    <script src="/_ah/channel/jsapi"></script>

**Create a channel (serverside)**

    ChannelServiceFactory.getChannelService().createChannel(CHANNELNAME);

**Join a channel (clientside)**

    import no.eirikb.gwtchannelapi.client.Channel;
    import no.eirikb.gwtchannelapi.client.Channel.ChannelListener;

    Channel.join(channelKey, new ChannelListener() { ... });

Send/Push messages from server (serverside)
All classes that should be pushed MUST implement Message

    import no.eirikb.gwtchannelapi.client.Message;
    public class MessageEvent implements Message {
    }

ChannelServer.sendEvent(CHANNELNAME, new MessageEvent("Hello world"));

