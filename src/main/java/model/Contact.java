package model;

/**
 * Created by Denis on 24.10.2015.
 */
public class Contact {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String phoneNumber;
    private int statusDel;

    public Contact(int id, String name, String surname, String login, String email, String phoneNumber, int statusDel) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.statusDel = statusDel;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatusDel() {
        return statusDel;
    }

    public void setStatusDel(int statusDel) {
        this.statusDel = statusDel;
    }
}
