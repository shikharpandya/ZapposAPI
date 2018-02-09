package com.menu.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "restaurant")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
@Getter
@Setter
@Data

public class RestaurantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long RestId;

    @NotNull
    private String RestName;

    @NotNull
    private String RestAddress;

    @NotNull
    private String RestDescription;

    @NotNull
    private double RestPriceFor2;

    @NotNull
    private double RestRating;

    @NotNull
    private String RestCuisine;


    // Getters and Setters ... (Omitted for brevity)
}