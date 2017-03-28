package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.model.room.Room;
import jp.ddd.server.infrastructure.RoomRepositoryCtm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface RoomRepository extends JpaRepository<Room, Integer>, RoomRepositoryCtm {
}
