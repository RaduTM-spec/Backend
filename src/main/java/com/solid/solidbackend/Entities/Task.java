package com.solid.solidbackend.Entities;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@DiscriminatorValue("task")
public class Task extends Message {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taken_by")
    private User takenBy;

    // Constructors, getters, and setters
}
