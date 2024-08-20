package com.start.doctorpatientappointment.appointments.repository;

import com.start.doctorpatientappointment.appointments.model.Appointments;
import com.start.doctorpatientappointment.enums.AppointmentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentsRepository extends MongoRepository<Appointments,String> {

    List<Appointments> findByUserId(String userId);

    List<Appointments> findByDoctorId(String doctorId);

    @Query("{appointmentStatus:  ?0}")
    List<Appointments> findByAppointmentStatus(AppointmentStatus status);


}
