package mx.edu.utez.ExamenU1.service.impl;

import mx.edu.utez.ExamenU1.entity.person.bean.PersonBean;
import mx.edu.utez.ExamenU1.entity.person.dao.PersonDao;
import mx.edu.utez.ExamenU1.entity.person.dto.PersonDto;
import mx.edu.utez.ExamenU1.service.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PersonImpl implements IPerson {

    @Autowired
    private PersonDao dao;
    public String generateCurp(PersonDto person) {
        int cont = 0, contV = 0;
        String curp = "";
        while(cont <= person.getAp1().length()-1){
            char letra = person.getAp1().charAt(cont);
            if(cont == 0)
                curp = curp + letra;
            if(letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U'
                    || letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u'
                    && cont <= 1){
                curp = curp + letra;
                contV++;
            }
            cont++;
        }
        curp = curp + person.getAp2().charAt(0) + person.getName().charAt(0);
        String fecha = String.valueOf(person.getFechaNac());
        //0123456789
        //2004-02-24
        curp = curp + fecha.charAt(2) + fecha.charAt(3) + fecha.charAt(5) + fecha.charAt(6)
                + fecha.charAt(8) + fecha.charAt(9);
        curp = curp + person.getSexo().charAt(0);
        curp = curp + person.getEstadoNac().charAt(0) + person.getEstadoNac().charAt(1);
        cont = 0; contV = 0;
        while(cont <= 3){
            char letra = person.getAp2().charAt(cont);
            if(cont == 0){
                curp = curp + letra;}
            if((letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U'
                    || letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') == false
                    && contV <= 2){
                contV++;
            }  else {
                curp = curp + letra;
            }
            cont++;
        }
        cont = 0;
        while (cont != 2) {
            int numero = (int) (Math.random() * 10 + 1);
            curp = curp + numero;
            cont++;
        }
        curp = curp.toUpperCase();
        return curp;
    }


    @Override
    public PersonBean save(PersonDto datos) {
        PersonBean person = PersonBean.builder()
                .id(datos.getId())
                .name(datos.getName())
                .ap1(datos.getAp1())
                .ap2(datos.getAp2())
                .estadoNac(datos.getEstadoNac())
                .fechaNac(datos.getFechaNac())
                .sexo(datos.getSexo())
                .curp(generateCurp(datos))
                .build();
        return dao.save(person);
    }

    @Override
    public PersonBean findById(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void delete(PersonBean person) {
        dao.delete(person);
    }

}
