package mx.edu.utez.ExamenU1.entity.person.bean;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "person")
public class PersonBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "primer_apellido")
    private String ap1;

    @Column(name = "segundo_apellido")
    private String ap2;

    @Column(name = "fecha_nacimiento")
    private String fechaNac;

    @Column(name = "estado_nacimiento")
    private String estadoNac;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "CURP", unique = true)
    private String curp;


}
