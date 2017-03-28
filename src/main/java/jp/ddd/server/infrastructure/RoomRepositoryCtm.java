package jp.ddd.server.infrastructure;

import jp.ddd.server.domain.model.room.Room;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomRepositoryCtm {

    ImmutableList<Room> findByDtDesc(Integer userId);

    Optional<Room> getOpt(Integer id);
}
