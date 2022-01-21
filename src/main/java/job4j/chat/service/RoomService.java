package job4j.chat.service;

import job4j.chat.entity.Room;
import job4j.chat.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAllRoom(){
        List<Room> rsl = new ArrayList<>();
        roomRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Optional<Room> findRoomById(int id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(int id) {
        Room room = new Room();
        room.setId(id);
        roomRepository.delete(room);
    }
}
