package patientcare.users;

import java.util.Date;

public class patient extends user {
    private Date birth;

    public patient(String name, String lname, String username, String email, String password, String number, String gender,Date birth) {
        super(name, lname, username, email, password, number, gender);
        this.birth = birth;
    }
}
