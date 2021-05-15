package patientcare.services;

import com.mongodb.*;

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

    public static void addAppointment(String date, String hour) {
        BasicDBObject appointment = new BasicDBObject();
        appointment.put("date",date);
        appointment.put("hour", hour);

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
    public static void dropDB () {
        scheduleCollection.drop();

    }
}
