package patientcare.users;


public class patient extends user {

    public String DOB;

    public patient(String name, String lname, String username, String email, String password, String number, String gender,boolean logged,String DOB) {
        super(name, lname, username, email, password, number, gender,logged);
        this.DOB = DOB;
    }
}
