package com.menu.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
@Getter
@Setter
@Data

public class MenuItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ItemId;

    @NotNull
    private String ItemName;

    @NotNull
    private String ItemDescription;

    @NotNull
    private double ItemPrice;

    @NotNull
    private Long ItemQuantity;

    @NotNull
    private Long MenuId;

    // Getters and Setters ... (Omitted for brevity)
}