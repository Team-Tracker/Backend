package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.models.Chat;
import com.example.models.Message;
import com.example.repositories.ChatRepository;
import com.example.repositories.MessageRepository;

import java.util.ArrayList;

@RequestMapping(path = "/")
public class MainController {

    private ChatRepository chatRepository;

    private MessageRepository messageRepository;

    /*
     * @PostMapping(path = "/send")
     * public @ResponseBody String postMessage(@RequestParam Integer
     * user_id, @RequestParam Integer chat_id,
     * 
     * @RequestParam String text) {
     * Message message = new Message();
     * message.setUser_id(user_id);
     * message.setChat_id(chat_id);
     * message.setText(text);
     * 
     * messageRepository.save(message);
     * 
     * return message.getId().toString();
     * }
     * 
     * @GetMapping(path = "/all")
     * public @ResponseBody Iterable<Message> getAllMessages() {
     * return messageRepository.findAll();
     * }
     * 
     * @GetMapping(path = "/chats")
     * public @ResponseBody Iterable<Chat> getChats(@RequestParam Integer user_id) {
     * return chatRepository.findByUser_id(user_id);
     * }
     * 
     * @GetMapping(path = "/messages")
     * public @ResponseBody Iterable<Message> getMessages(@RequestParam Integer
     * user_id, @RequestParam Integer chat_id) {
     * ArrayList<Message> messages = new ArrayList<Message>();
     * 
     * for (Message message : messageRepository.findAll()) {
     * if (message.getUser_id() == user_id && message.getChat_id() == chat_id) {
     * messages.add(message);
     * }
     * }
     * 
     * return messages;
     * }
     */

}