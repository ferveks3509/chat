package job4j.chat.control;

import job4j.chat.entity.Person;
import job4j.chat.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/person")
public class PersonControl {

    private final PersonService persons;

    public PersonControl(PersonService persons) {
        this.persons = persons;
    }

    @GetMapping("/")
    public List<Person> getAllPerson() {
        return StreamSupport.stream(
                persons.getAllPerson().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id) {
        return persons.findById(id);
    }

    @PostMapping("/")
    public Person savePerson(@RequestBody Person person) {
        return persons.savePerson(person);
    }

    @PutMapping("/")
    public Person updatePerson(@RequestBody Person person) {
        return persons.savePerson(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        persons.deletePerson(id);
    }
}
