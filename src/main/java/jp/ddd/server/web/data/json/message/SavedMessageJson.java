package jp.ddd.server.web.data.json.message;

import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.web.data.Json;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class SavedMessageJson implements Json {

    private Long messageId;
    /** yyyy/MM/dd hh:mm:ss */
    private String messageDt;
    /** yyyy/MM/dd hh:mm:ss */
    private String lastEditDt;

    public static SavedMessageJson create(Message message) {
        return SavedMessageJson.builder().messageId(message.getId()).messageDt(Dates.toString(message.getMessageDt()))
          .lastEditDt(Dates.toString(message.getLastEditDt())).build();
    }
}
