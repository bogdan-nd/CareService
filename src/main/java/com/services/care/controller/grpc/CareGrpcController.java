package com.services.care.controller.grpc;

import com.services.care.entity.Appointment;
import com.services.care.service.CareService;
import com.services.grpc.server.care.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@GrpcService
@AllArgsConstructor
public class CareGrpcController extends CareServiceGrpc.CareServiceImplBase {
    private final CareService careService;

    @Override
    public void getHorsesAppointment(IdRequest request, StreamObserver<AppointmentResponse> responseObserver) {
        String id = request.getId();
        UUID horseId = UUID.fromString(id);
        List<Appointment> horseAppointments = careService.getHorseAppointments(horseId);
        List<ProtoAppointment> protoAppointments = transformAppointments(horseAppointments);

        AppointmentResponse response = AppointmentResponse.newBuilder()
                .addAllAppointments(protoAppointments).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createAppointment(AppointmentRequest request, StreamObserver<AppointmentResponse> responseObserver) {
        UUID horseId = UUID.fromString(request.getHorseId());
        UUID specialistId = UUID.fromString(request.getSpecialistId());
       Appointment appointment = new Appointment(horseId,specialistId);
       careService.createAppointment(appointment);
       ProtoAppointment protoAppointment = appointmentToProto(appointment);

       AppointmentResponse response = AppointmentResponse.newBuilder()
               .addAppointments(protoAppointment).build();

       responseObserver.onNext(response);
       responseObserver.onCompleted();
    }

    private ProtoAppointment appointmentToProto(Appointment appointment) {
        String horseId = appointment.getHorseId().toString();
        String appointmentId = appointment.getId().toString();
        String specialistId = appointment.getSpecialistId().toString();

        ProtoAppointment protoAppointment = ProtoAppointment.newBuilder()
                .setId(appointmentId)
                .setHorseId(horseId)
                .setSpecialistId(specialistId)
                .build();

        return protoAppointment;
    }

    public List<ProtoAppointment> transformAppointments(List<Appointment> appointments) {
        List<ProtoAppointment> protoAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            ProtoAppointment protoAppointment = appointmentToProto(appointment);
            protoAppointments.add(protoAppointment);
        }

        return protoAppointments;
    }
}
