package com.projeto.restaurant.config;

import com.projeto.restaurant.entities.Reservation;
import com.projeto.restaurant.entities.RestaurantTable;
import com.projeto.restaurant.entities.User;
import com.projeto.restaurant.entities.enums.ReservationStatus;
import com.projeto.restaurant.repositories.ReservationRepository;
import com.projeto.restaurant.repositories.RestaurantTableRepository;
import com.projeto.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantTableRepository tableRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", passwordEncoder.encode("123456"), "CLIENT");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", passwordEncoder.encode("123456"), "CLIENT");
        User u3 = new User(null, "Bob Admin", "bob@gmail.com", "911111111", passwordEncoder.encode("123456"), "ADMIN");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        RestaurantTable t1 = new RestaurantTable(null, 1, 4);
        RestaurantTable t2 = new RestaurantTable(null, 2, 2);
        RestaurantTable t3 = new RestaurantTable(null, 3, 6);

        tableRepository.saveAll(Arrays.asList(t1, t2, t3));

        Reservation r1 = new Reservation(null, Instant.parse("2026-01-20T19:30:00Z"), 4, ReservationStatus.CONFIRMED, u1, t1);
        Reservation r2 = new Reservation(null, Instant.parse("2026-01-20T20:00:00Z"), 2, ReservationStatus.PENDING, u2, t2);

        Reservation r3 = new Reservation(null, Instant.parse("2026-01-21T18:00:00Z"), 2, ReservationStatus.FINISHED, u1, t3);

        reservationRepository.saveAll(Arrays.asList(r1, r2, r3));
    }
}