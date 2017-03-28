package jp.ddd.server.infrastructure.mysql;

import jp.ddd.server.domain.model.message.MessageRead;
import jp.ddd.server.infrastructure.MessageReadRepositoryCtm;
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
public class MessageReadRepositoryImpl implements MessageReadRepositoryCtm {
    @Autowired
    private EntityManager em;

}
