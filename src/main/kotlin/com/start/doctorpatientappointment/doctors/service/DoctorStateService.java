package com.start.doctorpatientappointment.doctors.service;

import com.start.doctorpatientappointment.doctors.model.DoctorState;
import com.start.doctorpatientappointment.doctors.repository.DoctorStateRepository;
import com.start.doctorpatientappointment.doctors.util.DoctorLogin;
import com.start.doctorpatientappointment.doctors.util.DoctorStateDto;
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

import static com.start.doctorpatientappointment.util.Validate.validateEmail;
import static com.start.doctorpatientappointment.util.Validate.validatePassword;

@Service
public class DoctorStateService {

    @Autowired
    private DoctorStateRepository repository;


    public ResponseEntity<String> doctorSignup(@NotNull DoctorStateDto input) {

        if (this.repository.findByEmail(input.getEmail()).isEmpty()) {
            if (validateEmail(input.getEmail())) {
                if (validatePassword(input.getPassword())) {
                    DoctorState doctor = new DoctorState();
                    doctor.setDoctorId(UUID.randomUUID().toString());
                    doctor.setEmail(input.getEmail());
                    doctor.setPassword(input.getPassword());
                    doctor.setName(input.getName());
                    doctor.setAge(input.getAge());
                    doctor.setBio(input.getBio());
                    doctor.setGender(input.getGender());

                    this.repository.save(doctor);
                    return new ResponseEntity<>("Doctor Created. ", HttpStatus.OK);
                } else
                    return new ResponseEntity<>("Check your Password in proper pattern", HttpStatus.BAD_REQUEST);
            } else
                return new ResponseEntity<>("Check your Email in proper pattern", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>("Please check your mail. Mail Already Existed.", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> doctorLogin(@NotNull DoctorLogin input) {
        if (this.repository.findByEmail(input.getEmail()).isPresent() && this.repository.findByEmail(input.getEmail()).get().getPassword().equals(input.getPassword()))
            return new ResponseEntity<>("Login Success. ", HttpStatus.OK);
        else
            return new ResponseEntity<>("Bad Credentials.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<DoctorState>> getDoctorProfileById(String doctorId) {
        if (this.repository.findById(doctorId).isPresent())
            return new ResponseEntity<>(this.repository.findById(doctorId), HttpStatus.OK);
        else
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Optional<DoctorState>> getDoctorProfileByEmail(String email) {
        if (this.repository.findByEmail(email).isPresent())
            return new ResponseEntity<>(this.repository.findByEmail(email), HttpStatus.OK);
        else
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Optional<DoctorState>> updateProfile(String doctorId, DoctorStateDto input) {

        if (this.repository.findById(doctorId).isPresent()) {
            Optional<DoctorState> doctor = this.repository.findById(doctorId);
            doctor.get().setEmail(input.getEmail());
            doctor.get().setPassword(input.getPassword());
            doctor.get().setName(input.getName());
            doctor.get().setAge(input.getAge());
            doctor.get().setBio(input.getBio());
            doctor.get().setGender(input.getGender());
            this.repository.save(doctor.get());
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> deleteDoctorProfileByEmail(String email) {
        if (this.repository.findByEmail(email).isPresent()) {
            this.repository.delete(this.repository.findByEmail(email).get());
            return new ResponseEntity<>("Doctor Profile Deleted.", HttpStatus.OK);
        } else
            return new ResponseEntity<>("given email does not exist.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteDoctorProfileById(String doctorId) {
        if (this.repository.findById(doctorId).isPresent()) {
            this.repository.delete(this.repository.findById(doctorId).get());
            return new ResponseEntity<>("Doctor Profile Deleted.", HttpStatus.OK);
        } else
            return new ResponseEntity<>("given DoctorId does not exist.", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<DoctorState>> getAllDoctorProfiles() {
        if (this.repository.findAll().isEmpty())
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<AllPageResponse> findBySearchString(@NotNull PageRequestDto pageRequestDto) {
        Page<DoctorState> result;

        PageRequest pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(),
                Sort.by("age").ascending());

        if (pageRequestDto.getSearch() != null && !pageRequestDto.getSearch().isEmpty()) {
            result = this.repository.findBySearchString(pageRequestDto.getSearch(), pageRequest);
        } else {
            result = this.repository.findAll(pageRequest);
        }

        if (result != null && !result.getContent().isEmpty()) {
            List<DoctorState> records = result.getContent();
            return new ResponseEntity<>(new AllPageResponse(result.getTotalElements(), records),HttpStatus.OK);
        } else
            return new ResponseEntity<>(new AllPageResponse(),HttpStatus.NO_CONTENT);

    }


}
