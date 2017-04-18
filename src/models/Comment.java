package models;

import java.util.Date;

/**
 * Created by caoquan on 4/18/17.
 */
public class Comment {
    private Date date;
    private int idComment;
    private int idUser;
    private String comment;
    private String user;
    private int pidComment;
    private int pidMessage;
    private int score;

    public Comment(int idComment, int idUser, String comment, String user, int pidComment) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.comment = comment;
        this.user = user;
        this.pidComment = pidComment;
        this.score = 20;
        this.date = new Date();
    }

    public Comment(int idComment, int idUser, String comment, int pidMessage, String user) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.comment = comment;
        this.user = user;
        this.pidMessage = pidMessage;
        this.score = 20;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPidComment() {
        return pidComment;
    }

    public void setPidComment(int pidComment) {
        this.pidComment = pidComment;
    }

    public int getPidMessage() {
        return pidMessage;
    }

    public void setPidMessage(int pidMessage) {
        this.pidMessage = pidMessage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "date=" + date +
                ", idComment=" + idComment +
                ", idUser=" + idUser +
                ", comment='" + comment + '\'' +
                ", user='" + user + '\'' +
                ", pidComment=" + pidComment +
                ", pidMessage=" + pidMessage +
                ", score=" + score +
                '}';
    }
}
