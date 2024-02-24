package com.frankmoley.lil.learningspring.util;

import com.frankmoley.lil.learningspring.data.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class AppStartUpEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    public AppStartUpEvent(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);
        System.out.println("==========================Guests==========================");
        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);
        System.out.println("==========================Reservations==========================");
        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);
        System.out.println("==========================Reservations by date==========================");
        System.out.println(this.reservationRepository.findReservationByReservationDate(Date.valueOf("2022-01-01")));
    }
}
