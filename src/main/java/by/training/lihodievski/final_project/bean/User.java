package by.training.lihodievski.final_project.bean;

public class User implements Entity {

    private long id;
    private String login;
    private String email;
    private String password;
    private double money;
    private RoleType roleType;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass () != o.getClass ()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (Double.compare (user.money, money) != 0) {
            return false;
        }
        if (login != null ? !login.equals (user.login) : user.login != null){
            return false;
        }
        if (email != null ? !email.equals (user.email) : user.email != null) {
            return false;
        }
        if (password != null ? !password.equals (user.password) : user.password != null) {
            return false;
        }
        return roleType == user.roleType;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode () : 0);
        result = 31 * result + (email != null ? email.hashCode () : 0);
        result = 31 * result + (password != null ? password.hashCode () : 0);
        temp = Double.doubleToLongBits (money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (roleType != null ? roleType.hashCode () : 0);
        return result;
    }

}
