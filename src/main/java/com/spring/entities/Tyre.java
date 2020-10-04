package com.spring.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Entity
@Getter
@Setter
public class Tyre{

    @Id
    @GeneratedValue
    private Long id;

    private String tyreName;
}
