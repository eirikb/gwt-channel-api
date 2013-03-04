This is a wrapper of Google App Engine [Channel API](https://developers.google.com/appengine/docs/java/channel/overview) for [Google Web Toolkit](https://developers.google.com/web-toolkit).

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
<inherits name="no.eirikb.gwtchannelapi.GwtChannelApi" />
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

    public void onMessage(String token, String channelName, String message) {
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
...
```

Client
------

```Java
channel = new Channel("SomeRandomChannel");
channel.addChannelListener(new ChannelListener() {

    @Override
    public void onMessage(String message) {
    }

    @Override
    public void onOpen() {
    }

    @Override
    public void onError(int code, String description) {
    }

    @Override
    public void onClose() {
    }
});
channel.join();
```

Send messages with __`channel.send(String)`__.

AutoBean
--------

The current version of gwt-channel-api only support sending and receiving Strings.  
Previous versions used IsSerializable, but without proper RPC support it is better to let users handle this themselves.  
One of the current preferable ways to handle serialization is to use AutoBean, please see examples in the demo:

 *  [ChatServerImpl.java](/eirikb/gwt-channel-api/blob/master/demo/src/main/java/no/eirikb/gwtchannelapidemo/server/ChatServiceImpl.java) serializing with [MyFactory.java](/eirikb/gwt-channel-api/blob/master/demo/src/main/java/no/eirikb/gwtchannelapidemo/server/MyFactory.java) and [MyMessage.java](/eirikb/gwt-channel-api/blob/master/demo/src/main/java/no/eirikb/gwtchannelapidemo/shared/MyMessage.java).
 * [GwtChannelApiDemo.java](/eirikb/gwt-channel-api/blob/master/demo/src/main/java/no/eirikb/gwtchannelapidemo/client/GwtChannelApiDemo.java) Deserializing client side.

Demo
====

There should be a working demo at http://gwt-channel-api.appspot.com/ .  
To build and run the demo in demo folder locally run `mvn gwt:run`.

Not using maven
===============

It should be possible to download the jar manually from here:  
http://repo1.maven.org/maven2/no/eirikb/gwt-channel-api/0.1/gwt-channel-api-0.3.jar
