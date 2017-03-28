package jp.ddd.server.domain.model.room;

import jp.ddd.server.domain.model.base.BaseEntity;
import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.domain.repository.RoomRepository;
import jp.ddd.server.domain.repository.RoomUserRepository;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.other.utils.enums.Deleted;
import lombok.*;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The persistent class for the room database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({ //
  @NamedQuery(name = "Room.findWithRoomUserByUidDtDesc", query = "SELECT r FROM Room r JOIN FETCH r.roomUsers ru WHERE r.userId=:uid AND r.deleted=0 AND ru.deleted=0 ORDER BY r.lastMessageDt DESC"),
  @NamedQuery(name = "Room.getOptWithRoomUserByRid", query = "SELECT r FROM Room r JOIN FETCH r.roomUsers ru WHERE r.id=:rid AND r.deleted=0 AND ru.deleted=0") })
public class Room extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte deleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_message_dt")
    private Date lastMessageDt;

    private String name;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<RoomUser> roomUsers;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Message> messages;

    public static Room create(Integer userId, String roomName, Date lastMessageDt) {
        return Room.builder().deleted(Deleted.PUBLIC.getCode()).lastMessageDt(lastMessageDt).name(roomName)
          .userId(userId).build();
    }

    /**
     * メッセージルームを新規作成します。
     * @param userId
     * @param roomName
     * @param joinUserIds
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public static Room registerWithRoomUser(RoomRepository roomRepository, RoomUserRepository roomUserRepository, //
      Integer userId, String roomName, ImmutableList<Integer> joinUserIds) {

        val entity = Room.create(userId, roomName, Dates.now());
        val result = roomRepository.save(entity);
        val userIds = joinUserIds.newWith(userId).toSet().toImmutable();
        RoomUser.register(roomUserRepository, result.getId(), userIds);
        return result;
    }

    public static Optional<Room> getOpt(Integer roomId, RoomRepository roomRepository) {
        return Optional.ofNullable(roomRepository.getOne(roomId));
    }

    /**
     * 最終メッセージ日時を更新します。
     * @param rep
     * @param roomId
     * @param dt
     */
    public static void updateLastMsgDt(RoomRepository rep, Integer roomId, Date dt) {
        Room entity = Optional.ofNullable(rep.getOne(roomId)).orElseThrow(() -> new NotFoundException());
        entity.setLastMessageDt(dt);
        rep.save(entity);
    }

    /**
     * 対象ルームからユーザを取得します。
     * @param roomId
     * @param rep
     * @return
     */
    public static ImmutableList<RoomUser> findRoomUser(Integer roomId, RoomRepository rep) {
        val room = rep.getOpt(roomId).orElseThrow(() -> new NotFoundException("対象のメッセージルームが存在しません。" + roomId));
        return DsLists.toImt(room.getRoomUsers());
    }

    /**
     * 対象ルームにユーザを追加します。
     * @param roomId
     * @param addUserIds
     * @param rep
     * @return
     */
    public static ImmutableList<RoomUser> addRoomUser(Integer roomId, ImmutableList<Integer> addUserIds,
      RoomUserRepository rep) {
        val roomUsers = addUserIds.collect(uid -> RoomUser.create(roomId, uid, Dates.now()));
        return DsLists.toImt(rep.save(roomUsers.castToList()));
    }
}