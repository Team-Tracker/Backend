package com.example.helpers;

import com.example.models.Chat;
import com.example.models.ChatGroup;
import com.example.repositories.ChatGroupRepository;
import com.example.repositories.ChatRepository;

public class ChatHelper {

    public static Integer createChatGroup(Integer userId, Integer otherUserId, ChatGroupRepository chatGroupRepository) {
        ChatGroup chatGroup = new ChatGroup();

        chatGroup.setName("Chat between " + userId + " and " + otherUserId);

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