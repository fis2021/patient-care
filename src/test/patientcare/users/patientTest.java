package patientcare.users;

import static org.junit.jupiter.api.Assertions.*;

public class patientTest {

    public static patient createTestPatient()
    {
        return new patient("testName",
                "testLname",
                "testUsername",
                "testmail",
                "testpassword",
                "testNumber",
                "testgender",
                false,
                "testDOB");
    }



}