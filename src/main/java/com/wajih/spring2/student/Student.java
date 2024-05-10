package com.wajih.spring2.student;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_SEQ")
    @SequenceGenerator(name="STUDENT_SEQ", sequenceName="student_id_seq",
            allocationSize = 1)
    @Column( name="id" )
    private Long id;

    @Column( name="name" )
    private String name;

    @Column( name="email" )
    private String email;

    @Column( name="age" )
    private int age;

}
