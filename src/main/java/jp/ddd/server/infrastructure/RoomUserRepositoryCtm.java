package jp.ddd.server.infrastructure;

import jp.ddd.server.domain.model.room.RoomUser;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomUserRepositoryCtm {

    RoomUser updateByUnq(Integer roomId, Integer userId);

    Optional<RoomUser> getOptByUnq(Integer roomId, Integer userId);
}
