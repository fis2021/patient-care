package patientcare;

import javafx.beans.property.SimpleStringProperty;

public class Doctor {
    private  final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty spec;
    private final SimpleStringProperty email;
    //private final SimpleStringProperty phnum;

    Doctor(String fName,String lName, String spec,String email){
        this.firstName= new SimpleStringProperty(fName);
        this.lastName= new SimpleStringProperty(lName);
        this.spec= new SimpleStringProperty(spec);
        this.email= new SimpleStringProperty(email);
        //this.phnum= new SimpleStringProperty(phnum);

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

    public String getSpec() {

        return spec.get();
    }
    public void setSpec(String Spec) {

        spec.set(Spec);
    }

    public String getEmail() {

        return email.get();
    }
    public void setEmail(String Email) {

        email.set(Email);
    }
    /*public String getPhnumber() {

        return phnum.get();
    }
    public void setPhnumber(String Phnumber) {

        phnum.set(Phnumber);
    }

     */
}
