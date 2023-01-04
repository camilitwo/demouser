package com.demo.demouserregist.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
//@Table(name="D_PHONE")
public class Phones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_phone")
    private Long idPhone;
    private String number;
    private String citycode;
    private String contrycode;
}
