package com.system.spice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "spice_details")
public class SpiceDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "spice_id", nullable = false)
    private Spice spice;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String ingredients;

    private String color;

    @Column(columnDefinition = "TEXT")
    private String usageInstructions;

    @Column(columnDefinition = "TEXT")
    private String storage;

    private String smell;

    private String expirationPeriod;

    private String packagingLocation;
    
    @Column(name = "created_at", updatable = false)
    private java.sql.Timestamp createdAt;
}