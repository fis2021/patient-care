package patientcare.exceptions;

public class UsernameExists extends  Exception{
    public UsernameExists(String username){
        super("Username:"+username+" already exists");
    }
}
