package com.hobbyist.hobbyist.models;

import com.hobbyist.hobbyist.repos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final HobbyRepository hobbyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    @Value("${app.env}")
    private String environment;

    public DatabaseSeeder(HobbyRepository hobbyRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, CategoryRepository categoryRepository) {
        this.hobbyRepository = hobbyRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
    }

    private void seedHobbies() {

        List<Hobby> hobbies = Arrays.asList(
                new Hobby("General", "Hello", true),
                new Hobby("Tech", "tech", true),
                new Hobby("Music", "music", true),
                new Hobby("Art", "art", true),
                new Hobby("Programming", "programming", true),
                new Hobby("Food", "food", true),
                new Hobby("Films", "film", true)
        );

        hobbyRepository.saveAll(hobbies);
    }

    private void seedCategories() {

        List<Category> hobbies = Arrays.asList(
                new Category(1L, "Hello"),
                new Category(2L, "tech"),
                new Category(3L, "music"),
                new Category(4L, "art")
        );

        categoryRepository.saveAll(hobbies);
    }

    private void seedUsers() {

        List<User> users = Arrays.asList(
                new User(1L, "fer", "fer", "fer@mail.com", "fer", passwordEncoder.encode("pass"), true),
                new User(2L, "a", "a", "a@mail.com", "a", passwordEncoder.encode("pass"), true),
                new User(3L, "b", "b", "b@mail.com", "b", passwordEncoder.encode("pass"), false),
                new User(4L, "c", "c", "c@mail.com", "c", passwordEncoder.encode("pass"), false),
                new User(5L, "d", "d", "d@mail.com", "d", passwordEncoder.encode("pass"), true)
        );

        userRepository.saveAll(users);
    }

    @Override
    public void run(String... strings) throws Exception {

        if (!environment.equals("dev")) {
            log.info("seed.db is not in dev mode, skip the seeders");
            return;
        }

        log.info("Deleting tags...");
        hobbyRepository.deleteAll();

        log.info("Deleting users...");
        userRepository.deleteAll();

        log.info("Deleting posts");
        categoryRepository.deleteAll();

        log.info("Seeding tags...");
        this.seedHobbies();

        log.info("Seeding users...");
        this.seedUsers();

        log.info("Seeding posts and comments...");
        this.seedCategories();

        log.info("Finished running seeders!");

    }
}
