package com.start.doctorpatientappointment.doctorappointment.repository;

import com.start.doctorpatientappointment.doctorappointment.model.DoctorAppointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAppointmentRepository extends MongoRepository<DoctorAppointment,String> {

    List<DoctorAppointment> findByUserId(String userId);

    List<DoctorAppointment> findByDoctorId(String doctorId);


}
