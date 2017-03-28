package jp.ddd.server.domain.model.message;

import jp.ddd.server.domain.model.base.BaseEntity;
import jp.ddd.server.other.utils.Dates;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the message_read database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "message_read")
@NamedQueries({//
  @NamedQuery(name = "MessageRead.findByUidAndMids",//
    query = "SELECT mr FROM MessageRead mr WHERE mr.userId=:uid AND mr.messageId IN (:mids)") })
public class MessageRead extends BaseEntity {
    private static final long serialVersionUID = 2377932200497420692L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_dt")
    private Date readDt;

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "user_id")
    private Integer userId;

    public static MessageRead create(Long messageId, Integer userId) {
        return MessageRead.builder()//
          .readDt(Dates.now())//
          .messageId(messageId)//
          .userId(userId)//
          .build();
    }
}