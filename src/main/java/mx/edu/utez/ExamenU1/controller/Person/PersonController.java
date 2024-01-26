package mx.edu.utez.ExamenU1.controller.Person;

import mx.edu.utez.ExamenU1.entity.person.bean.PersonBean;
import mx.edu.utez.ExamenU1.entity.person.dto.PersonDto;
import mx.edu.utez.ExamenU1.service.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = {"*"})
public class PersonController {
    @Autowired
    private IPerson service;

    @PostMapping("/")
    @Transactional
    public PersonDto create(@RequestBody PersonDto datos){
        PersonBean person = service.save(datos);
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .ap1(person.getAp1())
                .ap2(person.getAp2())
                .fechaNac(person.getFechaNac())
                .estadoNac(person.getEstadoNac())
                .sexo(person.getSexo())
                .curp(person.getCurp())
                .build();
    }

    @PutMapping("/")
    @Transactional
    public PersonBean update(@RequestBody PersonDto datos){
        PersonBean person = service.save(datos);
        PersonDto envio = PersonDto.builder()
                .id(datos.getId())
                .name(person.getName())
                .ap1(person.getAp1())
                .ap2(person.getAp2())
                .fechaNac(person.getFechaNac())
                .estadoNac(person.getEstadoNac())
                .sexo(person.getSexo())
                .build();
        return service.save(envio);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public PersonBean findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Integer id){
        PersonBean person = service.findById(id);
        service.delete(person);
    }

    public String generateCurp(PersonDto person) {
        int cont = 0, contV = 0;
        String curp = "";
        while(cont <= person.getAp1().length()){
            char letra = person.getAp1().charAt(cont);
            if(cont == 0)
                curp = curp + letra;
            if(letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U'
                    || letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u'
                    && cont != 1){
                curp = curp + letra;
                contV++;
            }
        }
        curp = curp + person.getAp2().charAt(0) + person.getName().charAt(0);
        String fecha = String.valueOf(person.getFechaNac());
        //0123456789
        //2004-02-24
        curp = curp + fecha.charAt(2) + fecha.charAt(3) + fecha.charAt(5) + fecha.charAt(6)
                + fecha.charAt(8) + fecha.charAt(9);
        curp = curp + person.getSexo().charAt(0);
        curp = curp + person.getEstadoNac().charAt(0)+person.getEstadoNac().charAt(1);
        cont = 0; contV = 0;
        while(cont <= person.getAp1().length()){
            char letra = person.getAp1().charAt(cont);
            if(cont == 0)
                curp = curp + letra;
            if((letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U'
                    || letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') == false
                    && contV != 2){
                contV++;
            }  else {
                curp = curp + letra;
            }
        }
        cont = 0;
        while (cont != 2) {
            int numero = (int) (Math.random() * 10 + 1);
            curp = curp + numero;
        }
        curp = curp.toUpperCase();
        return curp;
    }

}
