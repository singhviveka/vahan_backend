package com.vivek.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromLocation;
    private String toLocation;
    private Double offerPrice;
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;   // role = USER

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider; // role = PROVIDER
}
