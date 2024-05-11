package com.start.doctorpatientappointment.users.service;

import com.start.doctorpatientappointment.doctors.model.DoctorState;
import com.start.doctorpatientappointment.users.model.UserState;
import com.start.doctorpatientappointment.users.repository.UserStateRepository;
import com.start.doctorpatientappointment.users.util.UserLogin;
import com.start.doctorpatientappointment.users.util.UserStateDto;
import com.start.doctorpatientappointment.util.AllPageResponse;
import com.start.doctorpatientappointment.util.PageRequestDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.start.doctorpatientappointment.util.Validate.validateEmail;
import static com.start.doctorpatientappointment.util.Validate.validatePassword;

@Service
public class UserStateService {

    @Autowired
    private UserStateRepository repository;

    public ResponseEntity<String> userSignup(@NotNull UserStateDto input) {

        if (this.repository.findByEmail(input.getEmail()).isEmpty()) {
            if (validateEmail(input.getEmail())) {
                if (validatePassword(input.getPassword())) {
                    UserState user = new UserState();
                    user.setUserId(UUID.randomUUID().toString());
                    user.setEmail(input.getEmail());
                    user.setPassword(input.getPassword());
                    user.setName(input.getName());
                    user.setAge(input.getAge());
                    user.setBio(input.getBio());
                    user.setGender(input.getGender());

                    this.repository.save(user);
                    return new ResponseEntity<>("User Created. ", HttpStatus.OK);
                }else
                    return new ResponseEntity<>("Check your Password in proper pattern",HttpStatus.BAD_REQUEST);
            }else
                return new ResponseEntity<>("Check your Email in proper pattern",HttpStatus.BAD_REQUEST);
        }else
            return new ResponseEntity<>("Please check your mail. Mail Already Existed.",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> userLogin(@NotNull UserLogin input) {
        if (this.repository.findByEmail(input.getEmail()).isPresent() && this.repository.findByEmail(input.getEmail()).get().getPassword().equals(input.getPassword()))
            return new ResponseEntity<>("Login Success. ",HttpStatus.OK);
        else
            return new ResponseEntity<>("Bad Credentials.",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<UserState>> getUserProfileById(String userId){
        if (this.repository.findById(userId).isPresent())
            return new ResponseEntity<>(this.repository.findById(userId),HttpStatus.OK);
        else
            return new ResponseEntity<>(Optional.empty(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Optional<UserState>> getUserProfileByEmail(String email){
        if (this.repository.findByEmail(email).isPresent())
            return new ResponseEntity<>(this.repository.findByEmail(email),HttpStatus.OK);
        else
            return new ResponseEntity<>(Optional.empty(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Optional<UserState>> updateProfile(String userId, UserStateDto input) {

        if (this.repository.findById(userId).isPresent()){
            Optional<UserState> user = this.repository.findById(userId);
            user.get().setEmail(input.getEmail());
            user.get().setPassword(input.getPassword());
            user.get().setName(input.getName());
            user.get().setAge(input.getAge());
            user.get().setBio(input.getBio());
            user.get().setGender(input.getGender());
            this.repository.save(user.get());
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else
            return new ResponseEntity<>(Optional.empty(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> deleteUserProfileByEmail(String email) {
        if (this.repository.findByEmail(email).isPresent()) {
             this.repository.delete(this.repository.findByEmail(email).get());
             return new ResponseEntity<>("User Profile Deleted.",HttpStatus.OK);
        }else
            return new ResponseEntity<>("given email does not exist.",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteUserProfileById(String userId) {
        if (this.repository.findById(userId).isPresent()) {
            this.repository.delete(this.repository.findById(userId).get());
            return new ResponseEntity<>("User Profile Deleted.",HttpStatus.OK);
        }else
            return new ResponseEntity<>("given UserId does not exist.",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<UserState>> getAllUserProfiles() {
        if (this.repository.findAll().isEmpty())
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(this.repository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<AllPageResponse> findBySearchString(@NotNull PageRequestDto pageRequestDto) {
        Page<UserState> result;

        PageRequest pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(),
                Sort.by("age").ascending());

        if (pageRequestDto.getSearch() != null && !pageRequestDto.getSearch().isEmpty()) {
            result = this.repository.findBySearchString(pageRequestDto.getSearch(), pageRequest);
        } else {
            result = this.repository.findAll(pageRequest);
        }

        if (result != null && !result.getContent().isEmpty()) {
            List<UserState> records = result.getContent();
            return new ResponseEntity<>(new AllPageResponse(result.getTotalElements(), records),HttpStatus.OK);
        } else
            return new ResponseEntity<>(new AllPageResponse(),HttpStatus.NO_CONTENT);

    }


}
