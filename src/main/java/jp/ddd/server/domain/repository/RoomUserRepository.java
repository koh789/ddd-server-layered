package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.model.room.RoomUser;
import jp.ddd.server.infrastructure.RoomUserRepositoryCtm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface RoomUserRepository extends JpaRepository<RoomUser, Integer>, RoomUserRepositoryCtm {
}
