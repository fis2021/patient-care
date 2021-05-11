package patientcare.exceptions;

public class emptyTextFieldExceptions extends  Exception{
    public emptyTextFieldExceptions(){
        super("Text field should not be empty");
    }
}
