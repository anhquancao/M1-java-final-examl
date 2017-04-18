package protocolHandler;

import util.Constant;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * Created by caoquan on 4/18/17.
 */
public class ReadFileURLStreamHandlerFactory implements URLStreamHandlerFactory {
    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if (Constant.PROTOCOL.equals(protocol)) {
            return new ReadFileUrlStreamHandler();
        }

        return null;
    }
}
