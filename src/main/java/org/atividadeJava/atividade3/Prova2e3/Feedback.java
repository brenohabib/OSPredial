package org.atividadeJava.atividade3.Prova2e3;

public class Feedback {
    private int id;
    private int osId;
    private double stars;
    private String feedbackText;

    // Construtor, getters e setters
    public Feedback(int id, int osId, double stars, String feedbackText) {
        this.id = id;
        this.osId = osId;
        this.stars = stars;
        this.feedbackText = feedbackText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOsId() {
        return osId;
    }

    public void setOsId(int osId) {
        this.osId = osId;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
}
