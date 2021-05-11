package patientcare.services;
import com.mongodb.*;
import patientcare.users.patient;

import java.util.Base64;

public class UserService {

    private static MongoClient mongoClient;
    private static DBCollection doctorCollection;
    private static DBCollection patientCollection;
    private static patient loggedUser;
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

    public static void checkExistingUsername(String username) throws
    public static void addPatient (String fname, String lname, String mobilenum, String dob, String username, String password, String email, String gender){
        BasicDBObject user = new BasicDBObject();
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

    public static int validateLogin (String username,String password) {
        DBObject obj = new BasicDBObject("username",username);
        obj.put("password",encodePassword(password));
        DBCursor cursorPatient = patientCollection.find(obj);
        DBCursor cursorDoctor = doctorCollection.find(obj);

        if(cursorPatient.one() != null){
           /**//* UserService.loggedUser = new BasicDBObject(
                    (String)cursorPatient.one().get("")
            )*/
        }
        if(patientCollection.find(obj) != null){
            return 1;
        }
        if(doctorCollection.find(obj) != null) {
            return 1;
        }
        return 0;
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
