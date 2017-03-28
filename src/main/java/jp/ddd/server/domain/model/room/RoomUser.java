package jp.ddd.server.domain.model.room;

import jp.ddd.server.domain.model.base.BaseEntity;
import jp.ddd.server.domain.repository.RoomUserRepository;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.other.utils.enums.Deleted;
import lombok.*;
import org.eclipse.collections.api.set.ImmutableSet;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the room database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "room_user")
@Entity
@NamedQueries({ //
  @NamedQuery(name = "RoomUser.getWithDelByUnq", query = "SELECT r FROM RoomUser r WHERE r.roomId=:rid AND r.userId=:uid")
})
public class RoomUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte deleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_dt")
    private Date joinDt;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "user_id")
    private Integer userId;

    public static RoomUser create(Integer roomId, Integer userId, Date joinDt) {
        return RoomUser.builder().deleted(Deleted.PUBLIC.getCode()).joinDt(joinDt).roomId(roomId).userId(userId)
          .build();
    }

    public static ImmutableSet<RoomUser> register(RoomUserRepository rep, Integer roomId, ImmutableSet<Integer> userIds) {
        val joinDt = Dates.now();
        return userIds.collect(uid -> RoomUser.create(roomId, uid, joinDt)).collect(entity -> rep.save(entity));
    }

    public static ImmutableSet<RoomUser> update(RoomUserRepository rep, Integer roomId, ImmutableSet<Integer> userIds) {
        val joinDt = Dates.now();

        return userIds.collect(uid -> RoomUser.create(roomId, uid, joinDt)).collect(entity -> rep.save(entity));
    }
}