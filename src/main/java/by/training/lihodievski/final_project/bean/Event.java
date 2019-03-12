package by.training.lihodievski.final_project.bean;

public class Event implements Entity {

    private long id;
    private Competition competition;
    private Rate rate;
    private boolean payment;
    private double percent;
    private double winPercent;

    public Event() {
    }

    public Event(long eventId) {
        this.id = eventId;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public double getWinPercent() {
        return winPercent;
    }

    public void setWinPercent(double winPercent) {
        this.winPercent = winPercent;
    }

    @Override
    public String toString() {
        return "Event{" +
                "competition=" + competition +
                ", rate=" + rate +
                '}';
    }
}
