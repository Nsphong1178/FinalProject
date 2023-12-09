package com.example.finalproject;

public class ClassQuestion {
    private long id;
    private String name;

    private String AnswerA;
    private String AnswerB;
    private String AnswerC;
    private String AnswerD;

    private String category;

    private int rightCorrect;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getRightCorrect() {
        return rightCorrect;
    }
    public void setRightCorrect(int _rightCorrect) {
        this.rightCorrect = _rightCorrect ;
    }
    public String getName() {
        return name;
    }
    public void setName(String _name) {
        this.name = _name;
    }
    public String getAnswerA() {
        return AnswerA;
    }
    public void setAnswerA(String _AnswerA) {
        this.AnswerA = _AnswerA;
    }
    public String getAnswerB() {
        return AnswerB;
    }
    public void setAnswerB(String _AnswerB) {
        this.AnswerB = _AnswerB;
    }
    public String getAnswerC() {
        return AnswerC;
    }
    public void setAnswerC(String _AnswerC) {
        this.AnswerC = _AnswerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }
    public void setAnswerD(String _AnswerD) {
        this.AnswerD = _AnswerD;
    }



    // Will be used by the ArrayAdapter in the ListView

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ").append(name).append("\n");
        stringBuilder.append("AnswerA: ").append(AnswerA).append("\n");
        stringBuilder.append("AnswerB: ").append(AnswerB).append("\n");
        stringBuilder.append("AnswerC: ").append(AnswerC).append("\n");
        stringBuilder.append("AnswerD: ").append(AnswerD);

        return stringBuilder.toString();
    }



}
