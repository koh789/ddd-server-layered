package jp.ddd.server.infrastructure;

import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.other.data.common.Page;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Created by noguchi_kohei 
 */
public interface MessageRepositoryCtm {

    ImmutableList<Message> findByRoomId(Integer roomId, Page page);

    /**
     * 指定メッセージルーム内で対象ユーザの未読リストを取得します。
     * @param roomId
     * @param userId
     * @return
     */
    ImmutableList<Message> findUnread(Integer roomId, Integer userId);
}
