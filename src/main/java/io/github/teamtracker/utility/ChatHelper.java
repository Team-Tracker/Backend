package io.github.teamtracker.utility;

import io.github.teamtracker.model.Chat;
import io.github.teamtracker.model.ChatGroup;
import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.ChatRepository;

public class ChatHelper {

    private static final String CHAT_GROUP_NAME_SINGLE = "Chat";

    private static final String CHAT_GROUP_NAME = "Chat between %d and %d";

    public static Integer createChatGroup(Integer userId, ChatGroupRepository chatGroupRepository) {
        ChatGroup chatGroup = new ChatGroup();

        chatGroup.setName(ChatHelper.CHAT_GROUP_NAME_SINGLE);

        chatGroupRepository.save(chatGroup);

        return chatGroup.getId();
    }

    public static Integer createChatGroup(Integer userId, Integer otherUserId,
            ChatGroupRepository chatGroupRepository) {
        ChatGroup chatGroup = new ChatGroup();

        chatGroup.setName(String.format(ChatHelper.CHAT_GROUP_NAME, userId, otherUserId));

        chatGroupRepository.save(chatGroup);

        return chatGroup.getId();
    }

    public static Integer createChat(Integer userId, Integer chatGroupId, ChatRepository chatRepository) {
        Chat chat = new Chat();

        chat.setChatGroupId(chatGroupId);
        chat.setUserId(userId);

        chatRepository.save(chat);

        return chat.getId();
    }
}