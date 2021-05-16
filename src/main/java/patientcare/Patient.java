package patientcare;

import javafx.beans.property.SimpleStringProperty;

public class Patient {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty mobilenum;

    Patient(String fName,String lName,String email,String mobilenum){
        this.firstName= new SimpleStringProperty(fName);
        this.lastName= new SimpleStringProperty(lName);
        this.email= new SimpleStringProperty(email);
        this.mobilenum= new SimpleStringProperty(mobilenum);

    }
    public String getFirstName() {

        return firstName.get();
    }
    public void setFirstName(String fName) {

        firstName.set(fName);
    }

    public String getLastName() {

        return lastName.get();
    }
    public void setLastName(String lName) {

        lastName.set(lName);
    }


    public String getEmail() {

        return email.get();
    }
    public void setEmail(String Email) {

        email.set(Email);
    }
    public String getMobilenum() {

        return mobilenum.get();
    }
    public void setMobilenum(String Mobilenum) {

        mobilenum.set(Mobilenum);
    }


}
