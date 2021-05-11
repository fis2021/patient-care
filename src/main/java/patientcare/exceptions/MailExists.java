package patientcare.exceptions;

public class MailExists extends Exception{
    public MailExists(String mail){
        super("Mail:"+mail+" already exists");
    }

}
