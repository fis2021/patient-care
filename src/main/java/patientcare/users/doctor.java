package patientcare.users;

public class doctor extends user{
    public String spec;
    public doctor(String name, String lname, String username, String email, String password, String number, String gender,boolean logged,String spec) {
        super(name, lname, username, email, password, number, gender,logged);
        this.spec = spec;
    }


}
