package patientcare.services;

import com.mongodb.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {

    public static void addTestAppointment() {
        try{
            AppointmentService.addAppointment(
                    "testDoctor",
                    "testPatient",
                    "testDate",
                    "testHour");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void deleteTestAppointment(){

        BasicDBObject appointment = new BasicDBObject();
        appointment.put("date","testDate");
        appointment.put("hour"," testHour");
        appointment.put("doctor","testDoctor");
        appointment.put("patient","testPatient");

        DBCursor cursor = AppointmentService.getAppointmentCollection().find(appointment);
        if(cursor.hasNext()) AppointmentService.getAppointmentCollection().remove(cursor.next());
    }

    @Test
    public void Initialize(){
        AppointmentService.Initialize();
        DBCollection scheduleCollection = AppointmentService.getAppointmentCollection();
        DB database = scheduleCollection.getDB();

        Assertions.assertAll(   () -> assertEquals("Schedule",scheduleCollection.getName()),
                () ->  assertEquals("AppointmentsDB", database.getName()));
    }

    @Test
    public void addAppointment(){

        AppointmentService.Initialize();
        addTestAppointment();

        DBCursor cursor = AppointmentService.getAppointmentCollection().find();
        DBObject lastAppointmentAdded = cursor.one();


        Assertions.assertAll(
                () -> assertEquals("testDate", (String) lastAppointmentAdded.get("date")),
                () -> assertEquals("testHour", (String) lastAppointmentAdded.get("hour")),
                () -> assertEquals("testDoctor", (String) lastAppointmentAdded.get("doctor")),
                () -> assertEquals("testPatient", (String) lastAppointmentAdded.get("patient"))
        );

        deleteTestAppointment();
    }

    @Test
    public void appointmentExists(){
        addTestAppointment();

        assertEquals(true, AppointmentService.appointmentExists("testDoctor",
                "testDate",
                "testHour"));

        deleteTestAppointment();
    }
    @Test
    public void appointmentExistsByPatientAndDoctor(){
        addTestAppointment();

        assertEquals(true, AppointmentService. appointmentExistsByPatientAndDoctor("testPatient",
                "testDoctor"));

        deleteTestAppointment();
    }
}