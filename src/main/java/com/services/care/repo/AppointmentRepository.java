package com.services.care.repo;

import com.services.care.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query(value = "SELECT * FROM appointments app where app.horse_id = :id", nativeQuery = true)
    List<Appointment> getHorseAppointments(@Param("id") UUID horseId);
}
