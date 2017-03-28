package jp.ddd.server.web.data.json.message;

import jp.ddd.server.other.data.message.ReadDto;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.web.data.Json;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class ReadJson implements Json {

    private static final long serialVersionUID = 4329939331917276010L;

    private Long messageId;

    private Integer readUserId;

    private final String readUserName;

    private final String readDate;

    public static ReadJson create(ReadDto dto) {
        return ReadJson.builder()//
          .messageId(dto.getMessageId())//
          .readUserId(dto.getReadUserId())//
          .readUserName(dto.getReadUserName())//
          .readDate(Dates.toString(dto.getReadDate()))//
          .build();
    }
}
