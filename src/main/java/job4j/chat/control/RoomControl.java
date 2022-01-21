package job4j.chat.control;

import job4j.chat.entity.Room;
import job4j.chat.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomControl {

    private final RoomService rooms;

    public RoomControl(RoomService rooms) {
        this.rooms = rooms;
    }

    @GetMapping("/")
    public List<Room> findAllRooms() {
        return rooms.findAllRoom();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findRoomById(@PathVariable int id) {
        var room = rooms.findRoomById(id);
        return new ResponseEntity<>(
                room.orElse(new Room()),
                room.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public Room saveRoom(Room room) {
        if (room.getName() == null) {
            throw new NullPointerException("Введите название комнаты");
        }
        return rooms.saveRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable int id) {
        rooms.deleteRoom(id);
    }
}
