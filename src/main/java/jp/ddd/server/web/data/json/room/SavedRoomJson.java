package jp.ddd.server.web.data.json.room;

import jp.ddd.server.domain.model.room.Room;
import jp.ddd.server.web.data.Json;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class SavedRoomJson implements Json {
    private static final long serialVersionUID = 910139523300534581L;

    private Integer roomId;

    public static SavedRoomJson create(Room room) {

        return SavedRoomJson.builder().roomId(room.getId()).build();
    }
}
