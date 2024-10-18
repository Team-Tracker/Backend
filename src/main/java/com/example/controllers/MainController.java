package com.example.controllers;

// @RequestMapping(path = "/")
public class MainController {

    // private ChatRepository chatRepository;

    // private MessageRepository messageRepository;

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