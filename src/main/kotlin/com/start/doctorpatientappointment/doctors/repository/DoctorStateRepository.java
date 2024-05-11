package com.start.doctorpatientappointment.doctors.repository;

import com.start.doctorpatientappointment.doctors.model.DoctorState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorStateRepository extends MongoRepository<DoctorState,String> {

    Optional<DoctorState> findByEmail(String email);

    @Query("{'$or':[{'name':{$regex:?0,$options:'i'}}," + "{'email':{$regex:?0,$options:'i'}},"+
            "{'bio':{$regex:?0,$options:'i'}},"+"{'age':{$regex:?0,$options:'i'}},"+
            "{'gender':{$regex:?0,$options:'i'}}]}")
    Page<DoctorState> findBySearchString(String search, Pageable pageable);


}
