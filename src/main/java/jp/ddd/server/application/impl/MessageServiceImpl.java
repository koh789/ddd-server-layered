package jp.ddd.server.application.impl;

import jp.ddd.server.application.MessageService;
import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.domain.model.room.Room;
import jp.ddd.server.domain.model.user.User;
import jp.ddd.server.domain.repository.MessageReadRepository;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.domain.repository.RoomRepository;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.data.message.MessageDto;
import jp.ddd.server.other.data.message.RegisterMessageParam;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by noguchi_kohei 
 */
@Service
public class MessageServiceImpl implements MessageService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageReadRepository messageReadRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Message register(RegisterMessageParam param) {
        Room.updateLastMsgDt(roomRepository, param.getRoomId(), param.getMessageDt());
        return Message.register(param, messageRepository);
    }

    @Override
    public ImmutableList<MessageDto> find(Integer roomId, Integer userId, Page page){

        val messages =Message.loadAndSaveRead(roomId,userId,page,messageRepository, messageReadRepository);
        val roomUserIds = Room.findRoomUser(roomId, roomRepository).collect(r -> r.getUserId());
        val roomUserMap = User.find(roomUserIds, userRepository).groupByUniqueKey(u -> u.getId());

        return messages.collect(m -> MessageDto.create(m, roomUserMap));
    }
}
