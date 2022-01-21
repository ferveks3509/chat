package job4j.chat.service;

import job4j.chat.entity.Person;
import job4j.chat.repository.PersonRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson() {
        List<Person> rsl = new ArrayList<>();
        personRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public Person savePerson(Person person) {
         return personRepository.save(person);
    }

    public void deletePerson(int id) {
        Person person = new Person();
        person.setId(id);
        personRepository.delete(person);
    }
}
