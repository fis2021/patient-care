package patientcare.users;

import java.util.Date;

public abstract class user {
    public String fname;
    public String lname;
    public String username;
    public String email;
    public String number;
    public String password;
    public String gender;
    public boolean logged ;

    public user(String name,String lname,String username, String mail, String password, String number, String gender,boolean logged) {
        this.fname = name;
        this.lname = lname;
        this.username = username;
        this.email = mail;
        this.password = password;
        this.number = number;
        this.gender = gender;
        this.logged = logged;

    }
    public String getEmail() {
        return this.email;
    }

}
