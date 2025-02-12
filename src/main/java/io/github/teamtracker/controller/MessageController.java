package io.github.teamtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.misc.WordFilter;
import io.github.teamtracker.model.chat.Message;
import io.github.teamtracker.repository.MessageRepository;
import io.github.teamtracker.repository.WordlistRepository;
import io.github.teamtracker.socket.WebSocket.WebSocketHandler;

@Controller
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private WordlistRepository wordlistRepository;

    @Autowired
    private WebSocketHandler messageWebSocketHandler;

    private WordFilter wordFilter;

    @GetMapping(path = "/")
    public @ResponseBody Iterable<Message> getMessages() {
        return this.messageRepository.findAll();
    }

    @GetMapping(path = "/messages")
    public @ResponseBody Iterable<Message> getUserMessages(@RequestParam Integer userId) {
        Iterable<Message> messages = this.messageRepository.findByUserId(userId);

        return messages;
    }

    @GetMapping(path = "/messagesChat")
    public @ResponseBody Iterable<Message> getChatMessages(@RequestParam Integer chatGroupId) {
        Iterable<Message> messages = this.messageRepository.findByChatGroupId(chatGroupId);

        return messages;
    }

    @PostMapping(path = "/send")
    public @ResponseBody String postMessage(@RequestParam Integer userId, @RequestParam Integer chatGroupId,
            @RequestParam String text) throws Exception {
        if (this.wordFilter == null) {
            this.wordFilter = new WordFilter(this.wordlistRepository.findAll());
        }

        if (this.wordFilter.isProfane(text)) {
            text = this.wordFilter.filterText(text);
        }

        Message message = new Message();

        message.setUserid(userId);
        message.setChatGroupId(chatGroupId);
        message.setText(text);

        this.messageRepository.save(message);

        this.messageWebSocketHandler.broadcastMessage(message);

        return message.getId().toString();
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteMessage(@RequestParam Integer id) {
        this.messageRepository.deleteById(id);

        return id.toString();
    }
}