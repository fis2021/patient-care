package patientcare.services;

import com.mongodb.*;

import java.time.LocalDate;

public class AppointmentService {
    private static MongoClient mongoClient;
    private static DBCollection scheduleCollection;
    private static DB database;


    public static DBCollection getAppointmentCollection(){
        return AppointmentService.scheduleCollection;
    }

    public static void Initialize(){
        try{
            mongoClient = new MongoClient();
            database = mongoClient.getDB("AppointmentsDB");
            //database.getCollection("users");
            scheduleCollection = database.getCollection("Schedule");

        } catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void addAppointment(String doctor,String patient,String date, String hour) {
        BasicDBObject appointment = new BasicDBObject();
        appointment.put("date",date);
        appointment.put("hour", hour);
        appointment.put("doctor",doctor);
        appointment.put("patient",patient);

        scheduleCollection.insert(appointment);
    }
    public static void printAppointment(){
        DBCursor cursor = scheduleCollection.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
    }

    public static boolean appointmentExists(String doctor,String date,String hour){
        BasicDBObject appointment = new BasicDBObject();
        appointment.put("date",date);
        appointment.put("hour", hour);
        appointment.put("doctor",doctor);

        DBCursor cursor = scheduleCollection.find(appointment);
        if(cursor.one() != null) return true;

        return false;
    }

    public static boolean appointmentExistsByPatientAndDoctor(String patient,String doctor){
        BasicDBObject appointment = new BasicDBObject();
        appointment.put("doctor",doctor);
        appointment.put("patient",patient);


        DBCursor cursor = scheduleCollection.find(appointment);
        if(cursor.one() != null) return true;

        return false;
    }


    public static void deleteAppointment(String doctor,String patient,String date,String hour){
        BasicDBObject appointment = new BasicDBObject();
        appointment.put("date",date);
        appointment.put("hour", hour);
        appointment.put("doctor",doctor);
        appointment.put("patient",patient);

        DBCursor cursor = scheduleCollection.find(appointment);
        if(cursor.hasNext()) AppointmentService.scheduleCollection .remove(cursor.next());

    }

    public static void getAppointmentsByDoctor(String doctor){
        BasicDBObject appointment = new BasicDBObject("doctor",doctor);

        DBCursor cursor = scheduleCollection.find(appointment);
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
    }

    public static void getAppointmentsByPatient(String patient){
        BasicDBObject appointment = new BasicDBObject("patient",patient);

        DBCursor cursor = scheduleCollection.find(appointment);
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
    }
    public static void getAppointmentsByPatientAndDoctor(String patient,String doctor){
        BasicDBObject appointment = new BasicDBObject("patient",patient);
        appointment.put("doctor",doctor);

        DBCursor cursor = scheduleCollection.find(appointment);
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
            System.out.println();
        }
    }

    public static void dropDB () {
        scheduleCollection.drop();

    }
}
