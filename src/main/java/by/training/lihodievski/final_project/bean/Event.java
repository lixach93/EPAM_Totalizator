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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (payment != event.payment) return false;
        if (Double.compare (event.percent, percent) != 0) return false;
        if (Double.compare (event.winPercent, winPercent) != 0) return false;
        if (competition != null ? !competition.equals (event.competition) : event.competition != null) return false;
        return rate == event.rate;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (competition != null ? competition.hashCode () : 0);
        result = 31 * result + (rate != null ? rate.hashCode () : 0);
        result = 31 * result + (payment ? 1 : 0);
        temp = Double.doubleToLongBits (percent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits (winPercent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
