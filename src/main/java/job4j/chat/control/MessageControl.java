package job4j.chat.control;

import job4j.chat.entity.Message;
import job4j.chat.service.MessageService;
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
    public Message findMessageById(@PathVariable int id) {
        return messages.findMessageById(id);
    }

    @PostMapping("/")
    public Message saveMessage(@RequestBody Message message) {
        return messages.saveMessage(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessageById(@PathVariable int id) {
        messages.deleteMessage(id);
    }
}
