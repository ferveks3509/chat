package job4j.chat.control;

import job4j.chat.entity.Room;
import job4j.chat.service.RoomService;
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
    public Room findRoomById(@PathVariable int id) {
        return rooms.findRoomById(id);
    }

    @PostMapping("/")
    public Room saveRoom(Room room) {
        return rooms.saveRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable int id) {
        rooms.deleteRoom(id);
    }
}
