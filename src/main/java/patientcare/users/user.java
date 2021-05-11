package patientcare.users;

import java.util.Date;

abstract class user {
    public String fname;
    public String lname;
    public String username;
    public String mail;
    public String number;
    public String password;
    public String gender;

    public user(String name,String lname,String username, String mail, String password, String number, String gender) {
        this.fname = name;
        this.lname = lname;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.number = number;
        this.gender = gender;

    }

}
