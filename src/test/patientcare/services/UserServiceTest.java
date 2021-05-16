package patientcare.services;

import com.mongodb.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import patientcare.users.doctorTest;
import patientcare.users.patientTest;
import patientcare.users.user;
import patientcare.exceptions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    private static user patientTestUser = patientTest.createTestPatient();
    private static user doctorTestUser = doctorTest.createTestDoctor();

    public static void addTestPatient(){
        UserService.addPatient("testFname",
                "testLname",
                "testNr",
                "testDOB",
                "testUsername",
                "testPassword",
                "testEmail",
                "testGender");
    }

    public static void addTestDoctor(){
        UserService.addDoctor("testFname",
                "testLname",
                "testNr",
                "testSpec",
                "testUsername",
                "testPassword",
                "testEmail",
                "testGender");
    }

    public static void removeTestPatient() {
        BasicDBObject obj = new BasicDBObject();
        obj.put("fname","testFname");
        obj.put("lname","testLname");
        obj.put("mobilenum","testNr");
        obj.put("DOB","testDOB");
        obj.put("username","testUsername");
        obj.put("password","testPassword");
        obj.put("email","testEmail");
        obj.put("gender","testGender");

        DBCursor deleteUser = UserService.getPatientCollection().find(obj);
        UserService.getPatientCollection().remove(deleteUser.one());
    }

    public static void removeTestDoctor() {
        BasicDBObject obj = new BasicDBObject();
        obj.put("fname","testFname");
        obj.put("lname","testLname");
        obj.put("mobilenum","testNr");
        obj.put("spec","testSpec");
        obj.put("username","testUsername");
        obj.put("password","testPassword");
        obj.put("email","testEmail");
        obj.put("gender","testGender");

        DBCursor deleteUser = UserService.getDoctorCollection().find(obj);
        UserService.getDoctorCollection().remove(deleteUser.one());
    }

    @Test
    public void testInitializeDB(){
        UserService.Initialize();
        DBCollection patientCollention = UserService.getPatientCollection();
        DB database = patientCollention.getDB();

        Assertions.assertAll(   () -> assertEquals("Patients",patientCollention.getName()),
                () ->  assertEquals("UsersDB", database.getName()));

        DBCollection doctorCollection = UserService.getDoctorCollection();

        Assertions.assertAll(   () -> assertEquals("Doctors",doctorCollection.getName()),
                () ->  assertEquals("UsersDB", database.getName()));
    }

    @Test
    public void checkExistingUser(){
        UserService.Initialize();
        assertThrows(UsernameExists.class, () -> UserService.checkExistingUsername("bogdan"));
    }

    @Test
    public void checkExistingEmail(){
        UserService.Initialize();
        assertThrows(MailExists.class, () -> UserService.checkExistingMail("bogdan@"));
    }
    @Test
    public void encodePassword(){
        Assertions.assertAll(
                () -> assertEquals("dGVzdFBhc3N3b3JkMQ==", UserService.encodePassword("testPassword1")),
                () -> assertEquals("dGVzdFBhc3N3b3JkMg==", UserService.encodePassword("testPassword2"))
        );
    }

    @Test
    public void decodePassword(){
        Assertions.assertAll(
                () -> assertEquals( "testPassword1", UserService.decodePassword("dGVzdFBhc3N3b3JkMQ==")),
                () -> assertEquals( "testPassword2", UserService.decodePassword("dGVzdFBhc3N3b3JkMg=="))
        );
    }

    @Test
    public void addPatientUser(){

        addTestPatient();

        DBCursor cursor = UserService.getPatientCollection().find();
        DBObject lastUserAdded = cursor.one();
        while(cursor.hasNext())
        {
            lastUserAdded = cursor.next();
        }

        DBObject finalLastUserAdded = lastUserAdded;
        Assertions.assertAll(
                () -> assertEquals("testFname", (String) finalLastUserAdded.get("fname")),
                () -> assertEquals("testLname", (String) finalLastUserAdded.get("lname")),
                () -> assertEquals(UserService.encodePassword("testPassword"), (String) finalLastUserAdded.get("password")),
                () -> assertEquals("testNr", (String) finalLastUserAdded.get("mobilenum")),
                () -> assertEquals("testGender", (String) finalLastUserAdded.get("gender")),
                () -> assertEquals("testEmail", (String) finalLastUserAdded.get("email")),
                () -> assertEquals("testUsername", (String) finalLastUserAdded.get("username")),
                () -> assertEquals("testDOB", (String) finalLastUserAdded.get("DOB"))
        );

        removeTestPatient();
    }
    @Test
    public void addDoctorUser(){

        addTestDoctor();

        DBCursor cursor = UserService.getDoctorCollection().find();
        DBObject lastUserAdded = cursor.one();
        while(cursor.hasNext())
        {
            lastUserAdded = cursor.next();
        }

        DBObject finalLastUserAdded = lastUserAdded;
        Assertions.assertAll(
                () -> assertEquals("testFname", (String) finalLastUserAdded.get("fname")),
                () -> assertEquals("testLname", (String) finalLastUserAdded.get("lname")),
                () -> assertEquals(UserService.encodePassword("testPassword"), (String) finalLastUserAdded.get("password")),
                () -> assertEquals("testNr", (String) finalLastUserAdded.get("mobilenum")),
                () -> assertEquals("testGender", (String) finalLastUserAdded.get("gender")),
                () -> assertEquals("testEmail", (String) finalLastUserAdded.get("email")),
                () -> assertEquals("testUsername", (String) finalLastUserAdded.get("username")),
                () -> assertEquals("testSpec", (String) finalLastUserAdded.get("spec"))
        );

        removeTestPatient();
    }

    @Test
    public void validatePatientLogin(){
        addTestPatient();

        assertEquals(true, UserService.validateLogin("testUsername", "testPassword"));

        removeTestPatient();

    }
    @Test
    public void validateDoctorLogin(){
        addTestDoctor();

        assertEquals(true, UserService.validateLogin("testUsername", "testPassword"));

        removeTestDoctor();
    }


    @Test
    public void getPatientCollection(){
        DBCollection patientCollection = UserService.getPatientCollection();

        assertEquals("Patients", patientCollection.getName());
    }

    @Test
    public void getDoctorCollection(){
        DBCollection doctorCollection = UserService.getDoctorCollection();

        assertEquals("Doctors", doctorCollection.getName());
    }


}