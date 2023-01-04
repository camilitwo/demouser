package com.demo.demouserregist.model;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
//@Table(name="D_USER")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;
    private String name;

    private String email;

    private String password;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Phones> Phones;
}
