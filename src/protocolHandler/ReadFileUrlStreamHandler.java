package protocolHandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Created by caoquan on 4/18/17.
 */
public class ReadFileUrlStreamHandler extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        return new ReadFileUrlConnection(url);
    }
}
