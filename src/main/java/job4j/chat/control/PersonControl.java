package job4j.chat.control;

import job4j.chat.entity.Person;
import job4j.chat.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        var person = persons.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public Person savePerson(@RequestBody Person person) {
        if (person.getLogin() == null || person.getPassword() == null) {
            throw new NullPointerException("Логин или пароль не должны быть пустыми");
        }
        if (person.getPassword().length() < 3) {
            throw new IllegalArgumentException("Длинна пароля должна быть не менее 3 символов");
        }

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
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void handler(Exception e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(e.getMessage());

    }
}
