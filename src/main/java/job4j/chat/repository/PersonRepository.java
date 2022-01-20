package job4j.chat.repository;

import job4j.chat.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findByLogin(String login);
}
