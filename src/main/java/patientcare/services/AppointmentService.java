package patientcare.services;

import com.mongodb.*;

import java.time.LocalDate;

public class AppointmentService {
    private static MongoClient mongoClient;
    private static DBCollection scheduleCollection;
    private static DB database;

    public static void Initialize(){
        try{
            mongoClient = new MongoClient();
            database = mongoClient.getDB("UsersDB");
            //database.getCollection("users");
            scheduleCollection = database.getCollection("Schedule");

        } catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void addAppointment(String doctor,String date, String hour) {
        BasicDBObject appointment = new BasicDBObject();
        appointment.put("date",date);
        appointment.put("hour", hour);
        appointment.put("doctor",doctor);

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


    public static void deleteAppointment(String doctor,String date,String hour){
        BasicDBObject appointment = new BasicDBObject();
        appointment.put("date",date);
        appointment.put("hour", hour);
        appointment.put("doctor",doctor);

        DBCursor cursor = scheduleCollection.find(appointment);
        if(cursor.hasNext()) AppointmentService.scheduleCollection .remove(cursor.next());

    }

    public static void getAppointments(String doctor){
        BasicDBObject appointment = new BasicDBObject("doctor",doctor);

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
