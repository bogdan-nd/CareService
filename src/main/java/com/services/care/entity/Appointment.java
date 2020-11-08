package com.services.care.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity(name="appointments")
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    private UUID id;
    private UUID horseId;
    private UUID specialistId;

    public Appointment(UUID horseId, UUID specialistId){
        this.id = UUID.randomUUID();
        this.horseId = horseId;
        this.specialistId = specialistId;
    }

}
