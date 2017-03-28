package jp.ddd.server.other.data.message;

import jp.ddd.server.domain.model.message.MessageRead;
import jp.ddd.server.domain.model.user.User;
import jp.ddd.server.other.data.Dto;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.DsMaps;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.eclipse.collections.api.map.ImmutableMap;

import java.util.Date;

/**
 * 既読情報を表現するdtoです。
 * @author noguchi_kohei
 *
 */
@Builder
@Value
public class ReadDto implements Dto {
    private static final long serialVersionUID = 1L;

    private final Long messageId;

    private final Integer readUserId;

    private final String readUserName;

    private final Date readDate;

    public static ReadDto create(MessageRead entity, ImmutableMap<Integer, User> userMap) {
        val userName = DsMaps.getOpt(userMap, entity.getUserId()).map(u -> u.getUserInfo().getName())
          .orElseThrow(() -> new NotFoundException());
        return ReadDto.builder()//
          .messageId(entity.getMessageId())//
          .readUserId(entity.getUserId())//
          .readUserName(userName)//
          .readDate(entity.getReadDt()).build();
    }
}
