package jp.ddd.server.web.data.json.room;

import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.web.data.Json;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class SavedRoomUserJson implements Json {
    private static final long serialVersionUID = -6715594480633791675L;

    private Long roomUserId;

    private Integer userId;

    private String joinDt;

    public static SavedRoomUserJson create(Long roomUserId, Integer userId, Date joinDt) {
        return SavedRoomUserJson.builder().roomUserId(roomUserId).userId(userId).joinDt(Dates.toString(joinDt)).build();
    }
}
