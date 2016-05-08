package com.sudhanshu.tinderswipe;

/**
 * Created by sudhanshu on 7/5/16.
 */
public class Question {

    public Question(String question, String imageUrl1, String imageUrl2) {
        this.question = question;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    private String question;
    private String imageUrl1;
    private String imageUrl2;

}

