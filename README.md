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
    <version>0.3</version>
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

Server
------

Extend `no.eirikb.gwtchannelapi.server.ChannelServer` to create a servlet listening for joins and messages.

```Java
public class MyServer extends ChannelServer {
    public void onJoin(String token, String channelName) {
    }

    public void onMessage(String token, String channelName, IsSerializable message) {
        // Send the message to everyone in the channel
        send(channelName, message);
    }
}
```

This servlet receives GWT RPC calls on `RemoteServiceRelativePath` __channel__, so make sure to implement this in _web.xml_:

```XML
...
<servlet-mapping>
    <servlet-name>MyServlet</servlet-name>
    <url-pattern>/MyModule/channel</url-pattern>
</servlet-mapping>
```

Client
------

```Java
channel = new Channel("SomeRandomChannel");
channel.addChannelListener(new ChannelListener() {

    @Override
    public void onMessage(IsSerializable message) {
    }

    @Override
    public void onOpen() {
    }

    @Override
    public void onError() {
    }

    @Override
    public void onClose() {
    }
});
channel.join();
```

Send messages with `channel.send(IsSerializable)`.

Not using maven
===============

It should be possible to download the jar manually from here:  
http://repo1.maven.org/maven2/no/eirikb/gwt-channel-api/0.1/gwt-channel-api-0.3.jar
