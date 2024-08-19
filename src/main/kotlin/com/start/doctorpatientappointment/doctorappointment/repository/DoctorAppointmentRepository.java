package com.start.doctorpatientappointment.doctorappointment.repository;

import com.start.doctorpatientappointment.doctorappointment.model.DoctorAppointment;
import com.start.doctorpatientappointment.enums.AppointmentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAppointmentRepository extends MongoRepository<DoctorAppointment,String> {

    List<DoctorAppointment> findByUserId(String userId);

    List<DoctorAppointment> findByDoctorId(String doctorId);

    @Query("{appointmentStatus:  ?0}")
    List<DoctorAppointment> findByAppointmentStatus(AppointmentStatus status);


}
