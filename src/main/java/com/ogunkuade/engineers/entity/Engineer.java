package com.ogunkuade.engineers.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "engineers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Engineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String company;

}
