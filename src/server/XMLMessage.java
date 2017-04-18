package server;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by caoquan on 4/18/17.
 */
@XmlRootElement(name = "message")
public class XMLMessage {
    private String content;

    public XMLMessage(String content) {
        this.content = content;
    }

    public XMLMessage() {
    }

    @XmlElement(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
