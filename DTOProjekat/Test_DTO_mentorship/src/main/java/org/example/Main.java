package org.example;

import org.example.model.Location;
import org.example.model.User;
import org.example.repository.LocationRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // http://localhost:8080/users-location

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void run(String... args) throws Exception {
        Location location = new Location();
        location.setPlace("Serbia");
        location.setDescription("Serbia is best in Basketball");
        location.setLongitude(50.1);
        location.setLatitude(22.1);
        locationRepository.save(location);

        User user1 = new User();
        user1.setFirstName("Uros");
        user1.setLastName("Riz");
        user1.setEmail("Uros@email.com");
        user1.setPassword("Uros123");
        user1.setLocation(location);
        userRepository.save(user1);


        User user2 = new User();
        user2.setFirstName("Marko");
        user2.setLastName("Nee");
        user2.setEmail("Marko@email.com");
        user2.setPassword("Marko123");
        user2.setLocation(location);
        userRepository.save(user2);

    }
}