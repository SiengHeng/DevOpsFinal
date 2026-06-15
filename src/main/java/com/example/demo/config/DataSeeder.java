package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.enums.BookingStatus;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TerrainRepository terrainRepository;
    private final TerrainImageRepository terrainImageRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        // 1. Create Users
        User admin = User.builder()
                .username("admin")
                .password("admin123")
                .email("admin@example.com")
                .fullName("System Administrator")
                .build();

        User user1 = User.builder()
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .fullName("John Doe")
                .build();

        userRepository.saveAll(List.of(admin, user1));

        // 2. Create Terrains
        Terrain mountainView = Terrain.builder()
                .name("Mountain View Escape")
                .description("A beautiful terrain with a panoramic view of the mountains.")
                .pricePerDay(new BigDecimal("150.00"))
                .location("Highlands")
                .type("Mountain")
                .owner(admin)
                .build();

        Terrain lakesideResort = Terrain.builder()
                .name("Lakeside Tranquility")
                .description("Peaceful terrain right next to the crystal clear lake.")
                .pricePerDay(new BigDecimal("200.00"))
                .location("Lake District")
                .type("Lakeside")
                .owner(admin)
                .build();

        terrainRepository.saveAll(List.of(mountainView, lakesideResort));

        // 3. Create Terrain Images
        TerrainImage img1 = TerrainImage.builder()
                .imageUrl("https://example.com/images/mountain1.jpg")
                .terrain(mountainView)
                .build();

        TerrainImage img2 = TerrainImage.builder()
                .imageUrl("https://example.com/images/lake1.jpg")
                .terrain(lakesideResort)
                .build();

        terrainImageRepository.saveAll(List.of(img1, img2));

        // 4. Create Bookings
        Booking booking1 = Booking.builder()
                .terrain(mountainView)
                .user(user1)
                .startDate(LocalDate.now().plusDays(7))
                .endDate(LocalDate.now().plusDays(10))
                .totalPrice(new BigDecimal("450.00"))
                .status(BookingStatus.PENDING)
                .build();

        bookingRepository.save(booking1);

        // 5. Create Payments
        Payment payment1 = Payment.builder()
                .booking(booking1)
                .amount(new BigDecimal("450.00"))
                .paymentDate(LocalDateTime.now())
                .status(PaymentStatus.PAID)
                .transactionId("TXN-123456")
                .build();

        paymentRepository.save(payment1);

        // 6. Create Reviews
        Review review1 = Review.builder()
                .terrain(mountainView)
                .user(user1)
                .rating(5)
                .comment("Absolutely stunning! Highly recommend.")
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review1);

        // 7. Create Favorites
        Favorite fav1 = Favorite.builder()
                .terrain(lakesideResort)
                .user(user1)
                .build();

        favoriteRepository.save(fav1);

        System.out.println("Data Seeder: Sample data inserted successfully!");
    }
}
