This is a wrapper of Google App Engine [Channel API](http://code.google.com/appengine/docs/java/channel/overview.html) for [Google Web Toolkit](http://code.google.com/webtoolkit)

Setup
=====

Maven
-----

Add this to your pom.xml:

```XML
<dependency>
    <groupId>no.eirikb.gwtchannelapi</groupId>
    <artifactId>gwt-channel-api</artifactId>
    <version>0.1</version>
</dependency>
```

Module
------

Add this to your Module.gwt.xml:

```XML
<inherits name='no.eirikb.gwtchannelapi.GwtChannelApi' />
```

HTML
----

Add this to your index.html:

```HTML
<script src="/_ah/channel/jsapi"></script>
```

Usage
=====

Create a channel on server
--------------------------

```Java
ChannelServiceFactory.getChannelService().createChannel(CHANNELNAME);
```

Join a channel on client
------------------------

```Java
import no.eirikb.gwtchannelapi.client.Channel;
import no.eirikb.gwtchannelapi.client.Channel.ChannelListener;

Channel.join(channelKey, new ChannelListener() { ... });
```

Send/Push messages from server
------------------------------

All classes that should be pushed MUST implement Message

```Java
import no.eirikb.gwtchannelapi.client.Message;
    public class MessageEvent implements Message {
}
```

```Java
ChannelServer.sendEvent(CHANNELNAME, new MessageEvent("Hello world"));
```

Not using maven
===============

It should be possible to download the jar manually from here:  
http://repo1.maven.org/maven2/no/eirikb/gwt-channel-api/0.1/gwt-channel-api-0.1.jar
