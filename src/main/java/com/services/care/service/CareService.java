package com.services.care.service;

import com.services.care.entity.Appointment;
import com.services.care.repo.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CareService{
    private final AppointmentRepository repository;

    @Transactional
    public Appointment createAppointment(Appointment appointment){
        return repository.save(appointment);
    }

    @Transactional
    public List<Appointment> getHorseAppointments(UUID horseId){
        return repository.getHorseAppointments(horseId);
    }

}
