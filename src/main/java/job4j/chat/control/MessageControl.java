package job4j.chat.control;

import job4j.chat.entity.Message;
import job4j.chat.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageControl {

    private final MessageService messages;

    public MessageControl(MessageService messages) {
        this.messages = messages;
    }

    @GetMapping("/")
    public List<Message> getAllMessage() {
        return messages.getAllMessage();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findMessageById(@PathVariable int id) {
        var message = messages.findMessageById(id);
        return new ResponseEntity<>(
                message.orElse(new Message()),
                message.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public Message saveMessage(@RequestBody Message message) {
        if (message.getText() == null) {
            throw new NullPointerException("Введите сообщение");
        }
        return messages.saveMessage(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessageById(@PathVariable int id) {
        messages.deleteMessage(id);
    }
}
