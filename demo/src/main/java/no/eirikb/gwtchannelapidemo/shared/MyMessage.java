package no.eirikb.gwtchannelapidemo.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MyMessage implements IsSerializable {
    private String message;

    public MyMessage() {
    }

    public MyMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
