package jp.ddd.server.infrastructure.mysql;

import jp.ddd.server.domain.model.room.Room;
import jp.ddd.server.infrastructure.RoomRepositoryCtm;
import jp.ddd.server.other.utils.DsLists;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class RoomRepositoryImpl implements RoomRepositoryCtm {
    @Autowired
    private EntityManager em;

    @Override
    public ImmutableList<Room> findByDtDesc(Integer userId) {
        val results = em.createNamedQuery("Room.findWithRoomUserByUidDtDesc").setParameter("uid", userId)
          .getResultList();
        return DsLists.toImt(results);
    }

    @Override
    public Optional<Room> getOpt(Integer id) {
        val results = em //
          .createNamedQuery("Room.getOptWithRoomUserByRid")//
          .setParameter("rid", id).getResultList();
        return DsLists.getFirstOpt(results);
    }
}

