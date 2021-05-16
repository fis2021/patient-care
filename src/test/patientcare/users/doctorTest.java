package patientcare.users;

import static org.junit.jupiter.api.Assertions.*;

public class doctorTest {

    public static doctor createTestDoctor()
    {
        return new doctor("testName",
                "testLname",
                "testUsername",
                "testmail",
                "testpassword",
                "testNumber",
                "testgender",
                false,
                "testSpec");
    }

}