package job4j.chat.service;

import job4j.chat.entity.Message;
import job4j.chat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessage() {
        List<Message> rsl = new ArrayList<>();
        messageRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Optional<Message> findMessageById(int id) {
        return messageRepository.findById(id);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(int id) {
        Message message = new Message();
        message.setId(id);
        messageRepository.delete(message);
    }
}
