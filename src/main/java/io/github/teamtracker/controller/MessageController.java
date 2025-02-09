package io.github.teamtracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.teamtracker.model.chat.Message;
import io.github.teamtracker.repository.MessageRepository;
import io.github.teamtracker.socket.WebSocket.WebSocketHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private WebSocketHandler messageWebSocketHandler;

    @PostMapping(path = "/send")
    public @ResponseBody String postMessage(@RequestParam Integer userId, @RequestParam Integer chatId,
            @RequestParam String text) throws Exception {
        Message message = new Message();

        message.setUserid(userId);
        message.setChatGroupId(chatId);
        message.setText(text);

        this.messageRepository.save(message);

        this.messageWebSocketHandler.broadcastMessage(message);

        return message.getId().toString();
    }

    @GetMapping(path = "/messages")
    public @ResponseBody Iterable<Message> getMessages(@RequestParam Integer userId) {
        Iterable<Message> messages = this.messageRepository.findByUserId(userId);

        return messages;
    }
}