package com.epam.jwd.hotel_booking.model;


import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class Login {
    private long id;
    private String login = "";
    private String password = "";
    private boolean isAdmin;
    private String admin;

    public Login(long id, String login, String password, boolean isAdmin) {
        this.id = id;
        this.login = login;
        this.password = hashPassword(password);
        this.isAdmin = isAdmin;
        this.admin = isAdmin ? "true" : "false";
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));

    }

    public boolean hasLoginAndPassword(String login, String password){
        return (this.login.equals(login) && BCrypt.checkpw(password, this.password));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public void setPasswordWithoutHashing(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return id == login1.id
                && isAdmin == login1.isAdmin
                && Objects.equals(login, login1.login)
                && Objects.equals(password, login1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, isAdmin);
    }
}
