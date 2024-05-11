package com.start.doctorpatientappointment.users.repository;

import com.start.doctorpatientappointment.doctors.model.DoctorState;
import com.start.doctorpatientappointment.users.model.UserState;
import com.start.doctorpatientappointment.users.util.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStateRepository extends MongoRepository<UserState,String> {

    Optional<UserState> findByEmail(String email);

    @Query("{'email': ?0, 'password': ?0}")
    Optional<UserState> findByEmailAndPassword(String email, String password);

    @Query("{'$or':[{'name':{$regex:?0,$options:'i'}}," + "{'email':{$regex:?0,$options:'i'}},"+
            "{'bio':{$regex:?0,$options:'i'}},"+"{'age':{$regex:?0,$options:'i'}},"+
            "{'gender':{$regex:?0,$options:'i'}}]}")
    Page<UserState> findBySearchString(String search, Pageable pageable);
}
