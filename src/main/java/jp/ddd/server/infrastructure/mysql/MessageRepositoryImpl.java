package jp.ddd.server.infrastructure.mysql;

import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.infrastructure.MessageRepositoryCtm;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.utils.DsLists;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class MessageRepositoryImpl implements MessageRepositoryCtm {
    @Autowired
    private EntityManager em;

    @Override
    public ImmutableList<Message> findByRoomId(Integer roomId, Page page) {

        val results = em //
          .createNamedQuery("Message.findWithReadByRoomIdOrderByIdDesc")//
          .setParameter("rid", (Integer) roomId)//
          .setFirstResult(page.getOffset())//
          .setMaxResults(page.getLimit()) //
          .getResultList();
        return DsLists.toImt(results);
    }

    @Override
    public ImmutableList<Message> findUnread(Integer roomId, Integer userId) {
        val results = em.createNamedQuery("Message.findUnreadByRidAndUid")//
          .setParameter("rid", roomId)//
          .setParameter("uid", userId)//
          .getResultList();
        return DsLists.toImt(results);
    }
}
