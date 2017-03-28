package jp.ddd.server.domain.model.user;

import jp.ddd.server.domain.model.base.BaseEntity;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.data.user.UserParam;
import jp.ddd.server.other.exception.IllegalDataException;
import jp.ddd.server.other.utils.Hashes;
import jp.ddd.server.other.utils.enums.Deleted;
import lombok.*;
import org.eclipse.collections.api.list.ImmutableList;

import javax.persistence.*;
import java.util.Optional;

/**
 * The persistent class for the user database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({//
  @NamedQuery(name = "User.getByLidWithDel", query = "SELECT u FROM User u WHERE u.loginId=:lid"),
  @NamedQuery(name = "User.getByLidAndPass", query = "SELECT u FROM User u WHERE u.loginId=:lid AND u.pass=:pass AND u.deleted=0"),
  @NamedQuery(name = "User.findByIds", query = "SELECT u FROM User u WHERE u.id IN (:ids) AND u.deleted=0") })
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte deleted;

    @Column(name = "login_id")
    private String loginId;

    private String pass;

    @Embedded
    private UserInfo userInfo;

    public static User create(UserParam param, String hashPass) {

        val userInfo = UserInfo.builder().email(param.getEmail()).tel(param.getTel()).name(param.getName()).build();
        return User.builder().deleted(Deleted.PUBLIC.getCode()).loginId(param.getLoginId()).pass(hashPass)
          .userInfo(userInfo).build();
    }

    public static User register(UserRepository rep, UserParam param) {
        if (isExist(rep, param.getLoginId())) {
            throw new IllegalDataException("登録済みloginIdです。" + param.getLoginId());
        }
        val hashPass = Hashes.toSHA256(param.getPassword());
        return rep.save(create(param, hashPass));
    }

    public static boolean isExist(UserRepository rep, String loginId) {
        return rep.getOpt(loginId).isPresent();
    }

    public static Optional<User> getOpt(UserRepository rep, String loginId, String pass) {
        val hashedPass = Hashes.toSHA256(pass);
        return rep.getOpt(loginId, hashedPass);
    }

    public static ImmutableList<User> find(ImmutableList<Integer> userIds, UserRepository rep) {
        return rep.find(userIds);
    }
}