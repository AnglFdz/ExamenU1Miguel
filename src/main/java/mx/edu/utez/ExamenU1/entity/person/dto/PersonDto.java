package mx.edu.utez.ExamenU1.entity.person.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonDto {

    private Long id;
    private String name;
    private String ap1;
    private String ap2;
    private String fechaNac;
    private String estadoNac;
    private String sexo;
    private String curp;

}
