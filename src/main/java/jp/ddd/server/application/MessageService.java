package jp.ddd.server.application;

import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.data.message.MessageDto;
import jp.ddd.server.other.data.message.RegisterMessageParam;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Created by noguchi_kohei 
 */
public interface MessageService {

    Message register(RegisterMessageParam param);

    ImmutableList<MessageDto> find(Integer roomId, Integer userId, Page page);
}
