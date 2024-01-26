package mx.edu.utez.ExamenU1.service;

import mx.edu.utez.ExamenU1.entity.person.bean.PersonBean;
import mx.edu.utez.ExamenU1.entity.person.dto.PersonDto;

public interface IPerson {
    PersonBean save(PersonDto datos);

    PersonBean findById(Integer id);

    void delete(PersonBean person);

}
