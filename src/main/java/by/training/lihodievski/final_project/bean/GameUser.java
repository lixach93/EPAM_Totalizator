package by.training.lihodievski.final_project.bean;

public class GameUser extends User {

    private double money;

    public GameUser(String name, String login, String password, RoleType roleType, double money) {
        super(name, login, password, roleType);
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GameUser gameUser = (GameUser) o;

        return Double.compare(gameUser.money, money) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
