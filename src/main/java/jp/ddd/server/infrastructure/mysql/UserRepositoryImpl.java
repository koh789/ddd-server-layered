package jp.ddd.server.infrastructure.mysql;

import jp.ddd.server.domain.model.user.User;
import jp.ddd.server.infrastructure.UserRepositoryCtm;
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
public class UserRepositoryImpl implements UserRepositoryCtm {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<User> getOpt(String loginId) {
        val results = em //
          .createNamedQuery("User.getByLidWithDel") //
          .setParameter("lid", (String) loginId) //
          .getResultList();
        return DsLists.getFirstOpt(results);
    }

    @Override
    public Optional<User> getOpt(String loginId, String hashedPass) {
        val results = em. //
          createNamedQuery("User.getByLidAndPass") //
          .setParameter("lid", loginId) //
          .setParameter("pass", hashedPass) //
          .getResultList();
        return DsLists.getFirstOpt(results);
    }

    @Override
    public ImmutableList<User> find(ImmutableList<Integer> userIds) {
        val results = em //
          .createNamedQuery("User.findByIds")//
          .setParameter("ids", userIds.castToList()) //
          .getResultList();
        return DsLists.toImt(results);
    }
}
