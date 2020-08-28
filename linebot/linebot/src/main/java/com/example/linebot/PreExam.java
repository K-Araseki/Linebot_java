package com.example.linebot;

public class PreExam {
    private int questionNumber;
    private String monndai;
    private String sentaku1;
    private String sentaku2;
    private String sentaku3;
    private String sentaku4;
    private int answer;
    private String kaisetu;

    public PreExam(int questionNumber, String monndai, String sentaku1, String sentaku2, String sentaku3, String sentaku4, int answer, String kaisetu) {
        this.questionNumber = questionNumber;
        this.monndai = monndai;
        this.sentaku1 = sentaku1;
        this.sentaku2 = sentaku2;
        this.sentaku3 = sentaku3;
        this.sentaku4 = sentaku4;
        this.answer = answer;
        this.kaisetu = kaisetu;
    }

    public void print(){
        System.out.println(monndai+sentaku1+sentaku2+sentaku3+sentaku4+answer+kaisetu);
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getMonndai() {
        return monndai;
    }

    public String getSentaku1() {
        return sentaku1;
    }

    public String getSentaku2() {
        return sentaku2;
    }

    public String getSentaku3() {
        return sentaku3;
    }

    public String getSentaku4() {
        return sentaku4;
    }

    public int getAnswer() {
        return answer;
    }

    public String getKaisetu() {
        return kaisetu;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setMonndai(String monndai) {
        this.monndai = monndai;
    }

    public void setSentaku1(String sentaku1) {
        this.sentaku1 = sentaku1;
    }

    public void setSentaku2(String sentaku2) {
        this.sentaku2 = sentaku2;
    }

    public void setSentaku3(String sentaku3) {
        this.sentaku3 = sentaku3;
    }

    public void setSentaku4(String sentaku4) {
        this.sentaku4 = sentaku4;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setKaisetu(String kaisetu) {
        this.kaisetu = kaisetu;
    }
}
