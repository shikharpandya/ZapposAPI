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

public class MenuModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long MenuId;

    @NotNull
    private String MenuName;

    @NotNull
    private String MenuDescription;

    @NotNull
    private String MenuSection;

    @NotNull
    private Long RestId;

    // Getters and Setters ... (Omitted for brevity)
}