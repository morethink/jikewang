package studio.geek.entity;



/**
 * Created by think on 2017/2/1.
 */

public class Manager{

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Manager)) return false;

        Manager manager = (Manager) object;

        if (!getUsername().equals(manager.getUsername())) return false;
        return getPassword().equals(manager.getPassword());

    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
