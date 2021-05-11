package patientcare.users;

public class doctor extends user{
    private String specialization;
    public doctor(String name, String lname, String username, String mail, String password, String number, String gender,String specialization) {
        super(name, lname, username, mail, password, number, gender);
        this.specialization = specialization;
    }
}
