package mx.edu.utez.ExamenU1.entity.person.dao;

import mx.edu.utez.ExamenU1.entity.person.bean.PersonBean;
import org.springframework.data.repository.CrudRepository;

public interface PersonDao extends CrudRepository<PersonBean, Integer> {
}
