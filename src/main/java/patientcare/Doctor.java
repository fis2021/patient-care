package patientcare;

import javafx.beans.property.SimpleStringProperty;

public class Doctor {
    private  final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty spec;

    Doctor(String fName,String lName, String spec){
        this.firstName= new SimpleStringProperty(fName);
        this.lastName= new SimpleStringProperty(lName);
        this.spec= new SimpleStringProperty(spec);


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

}
