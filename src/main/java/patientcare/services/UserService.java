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

    private static DBCollection reviewCollection;


    public static user loggedUser;
    private static DB database;
    public static String doctor_mail_for_appointment;

    public static DBCollection getReviewCollection(){
        return UserService.reviewCollection;
    }
  
    public static DBCollection getDoctorCollection(){
        return UserService.doctorCollection;
    }

    public static void Initialize(){
        try{
            mongoClient = new MongoClient();
            database = mongoClient.getDB("UsersDB");
            //database.getCollection("users");
            doctorCollection = database.getCollection("Doctors");
            patientCollection = database.getCollection("Patients");

            reviewCollection = database.getCollection("Reviews");
        } catch(Exception e)
        {
            System.out.println(e);
        }


    }
    public static void addReview (String  review,String patientMail,String doctorMail){
        BasicDBObject userReview = new BasicDBObject();
        userReview.put("review", review);
        userReview.put("patient",patientMail);
        userReview.put("doctor",doctorMail);

        reviewCollection.insert(userReview);

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


    public static void printReview(){
        DBCursor cursor = reviewCollection.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
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
        reviewCollection.drop();
    }

    public static boolean validateLogin (String username,String password) {
        DBObject obj = new BasicDBObject("username",username);
        obj.put("password",encodePassword(password));

        if(validatePatientLogin(obj) != null){
            loggedUser = validatePatientLogin(obj);
            return true;
        }else if (validateDoctorLogin(obj) != null){
            loggedUser = validateDoctorLogin(obj);
            System.out.println(loggedUser);
            return true;

        }


     return false;
    }

    public static patient validatePatientLogin(DBObject obj){
        DBCursor cursor = patientCollection.find(obj);
        if(cursor.one() == null){
            return null;
        }

        return new patient(
                (String)cursor.one().get("fname"),
                (String)cursor.one().get("lname"),
                (String)cursor.one().get("username"),
                (String)cursor.one().get("email"),
                (String)cursor.one().get("password"),
                (String)cursor.one().get("mobilenum"),
                (String)cursor.one().get("gender"),
                true,
                (String)cursor.one().get("DOB")
        );
    }

    public static doctor validateDoctorLogin(DBObject obj){
        DBCursor cursor = doctorCollection.find(obj);
        if(cursor.one() == null){
            return null;
        }

        return new doctor(
                (String)cursor.one().get("fname"),
                (String)cursor.one().get("lname"),
                (String)cursor.one().get("username"),
                (String)cursor.one().get("email"),
                (String)cursor.one().get("password"),
                (String)cursor.one().get("mobilenum"),
                (String)cursor.one().get("gender"),
                true,
                (String)cursor.one().get("spec"));

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
