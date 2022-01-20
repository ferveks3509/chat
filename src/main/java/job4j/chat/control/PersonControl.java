package job4j.chat.control;

import job4j.chat.entity.Person;
import job4j.chat.service.PersonService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/person")
public class PersonControl {

    private final PersonService persons;
    private final BCryptPasswordEncoder passwordEncoder;

    public PersonControl(PersonService persons, BCryptPasswordEncoder passwordEncoder) {
        this.persons = persons;
        this.passwordEncoder = passwordEncoder;
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

    @PostMapping("/sing-up")
    public Person savePerson(@RequestBody Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
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
