package patientcare.services;
import com.mongodb.*;
import patientcare.exceptions.MailExists;
import patientcare.exceptions.UsernameExists;
import patientcare.users.doctor;
import patientcare.users.patient;
import patientcare.users.user;

import java.util.Base64;
import java.util.Date;

public class UserService {

    private static MongoClient mongoClient;
    private static DBCollection doctorCollection;
    private static DBCollection patientCollection;
    public static user loggedUser;
    private static DB database;

    public static void Initialize(){
        try{
            mongoClient = new MongoClient();
            database = mongoClient.getDB("UsersDB");
            //database.getCollection("users");
            doctorCollection = database.getCollection("Doctors");
            patientCollection = database.getCollection("Patients");

        } catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public static void addDoctor(String fname, String lname, String mobilenum, String spec, String username, String password, String email, String gender){
        BasicDBObject user = new BasicDBObject();
        password = encodePassword(password);
        user.put("fname", fname);
        user.put("lname", lname);
        user.put("mobilenum", mobilenum);
        user.put("spec", spec);
        user.put("username", username);
        user.put("password", password);
        user.put("email", email);
        user.put("gender", gender);

        doctorCollection.insert(user);

    }

    public static void checkExistingUsername(String username) throws UsernameExists{
        DBObject query = new BasicDBObject("username", username);
        DBCursor cursorPatient = patientCollection.find(query);
        DBCursor cursorDoctor = doctorCollection.find(query);
        if(cursorPatient.one() != null)
        {
            throw new UsernameExists(username);
        }
        if(cursorDoctor.one() != null){
            throw new UsernameExists(username);
        }
    }

    public static void checkExistingMail(String mail) throws MailExists{
        DBObject query = new BasicDBObject("email", mail);
        DBCursor cursorPatient = patientCollection.find(query);
        DBCursor cursorDoctor = doctorCollection.find(query);
        if(cursorPatient.one() != null)
        {
            throw new MailExists(mail);
        }
        if(cursorDoctor.one() != null){
            throw new MailExists(mail);
        }
    }

    public static void addPatient (String fname, String lname, String mobilenum, String dob, String username, String password, String email, String gender){
        BasicDBObject user = new BasicDBObject();
        password = encodePassword(password);
        user.put("fname", fname);
        user.put("lname", lname);
        user.put("mobilenum", mobilenum);
        user.put("DOB", dob);
        user.put("username", username);
        user.put("password", password);
        user.put("email", email);
        user.put("gender", gender);

        patientCollection.insert(user);
    }

    public static void printDoctors(){
        DBCursor cursor = doctorCollection.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
    }
    public static void printPatients(){
        DBCursor cursor = patientCollection.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
    }
    public static void dropDB () {
        doctorCollection.drop();
        patientCollection.drop();
    }

    public static boolean validateLogin (String username,String password) {
        DBObject obj = new BasicDBObject("username",username);
        obj.put("password",encodePassword(password));
        DBCursor cursorPatient = patientCollection.find(obj);
        DBCursor cursorDoctor = doctorCollection.find(obj);

        if(cursorPatient.one() != null){
            UserService.loggedUser = new patient(
                    (String)cursorPatient.one().get("fname"),
                    (String)cursorPatient.one().get("lname"),
                    (String)cursorPatient.one().get("username"),
                    (String)cursorPatient.one().get("email"),
                    (String)cursorPatient.one().get("password"),
                    (String)cursorPatient.one().get("mobilenum"),
                    (String)cursorPatient.one().get("gender")
                   );

            return true;
        }else if(cursorDoctor.one() != null ){
            UserService.loggedUser = new doctor(
                    (String)cursorPatient.one().get("fname"),
                    (String)cursorPatient.one().get("lname"),
                    (String)cursorPatient.one().get("username"),
                    (String)cursorPatient.one().get("email"),
                    (String)cursorPatient.one().get("password"),
                    (String)cursorPatient.one().get("mobilenum"),
                    (String)cursorPatient.one().get("gender"));
            return true;
        }
     return false;
    }

    public static String encodePassword(String password){

        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        return encodedPassword;
    }

    public static String decodePassword(String encodedPassword){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        String decodedPassword = new String(decodedBytes);
        return decodedPassword;
    }
}
