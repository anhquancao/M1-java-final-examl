package models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by caoquan on 4/18/17.
 */
public class Message implements Serializable {
    private Date date;
    private int idMessage;
    private int idUser;
    private String message;
    private String user;
    private int score;
    private int value;


    public Message(int idMessage, int idUser, String message, String user) {
        this.idMessage = idMessage;
        this.idUser = idUser;
        this.message = message;
        this.user = user;
        score = 20;
        date = new Date();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Message{" +
                "date=" + date +
                ", idMessage=" + idMessage +
                ", idUser=" + idUser +
                ", message='" + message + '\'' +
                ", user='" + user + '\'' +
                ", score=" + score +
                ", value=" + value +
                '}';
    }
}
