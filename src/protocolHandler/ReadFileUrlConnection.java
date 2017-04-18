package protocolHandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by caoquan on 4/18/17.
 */
public class ReadFileUrlConnection extends URLConnection {
    protected ReadFileUrlConnection(URL url) {
        super(url);
    }

    @Override
    public void connect() throws IOException {
        System.out.println("Connected!");
    }
}
