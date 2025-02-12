package io.github.teamtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.calender.Event;
import io.github.teamtracker.repository.CalenderRepository;

@Controller
@RequestMapping(path = "/calender")
public class CalenderController {

    @Autowired
    private CalenderRepository calenderRepository;

    @GetMapping(path = "/events")
    public @ResponseBody Iterable<Event> getEvents(@RequestParam Integer userId) {
        Iterable<Event> events = this.calenderRepository.findByUserId(userId);

        return events;
    }

    @PostMapping(path = "/create")
    public @ResponseBody Integer createEvent(@RequestParam Integer userId, @RequestParam String title,
            @RequestParam String description, @RequestParam String date, @RequestParam String time,
            @RequestParam String duration) {
        Event event = new Event();

        event.setUserId(userId);
        event.setEventName(title);
        event.setEventDescription(description);
        event.setEventDate(date);
        event.setEventTime(time);
        event.setEventDuration(duration);

        this.calenderRepository.save(event);

        return event.getId();
    }

    @PostMapping(path = "/update")
    public @ResponseBody Integer updateEvent(@RequestParam Integer eventId, @RequestParam String title,
            @RequestParam String description, @RequestParam String date, @RequestParam String time,
            @RequestParam String duration) {
        Event event = this.calenderRepository.findById(eventId).get();

        event.setEventName(title);
        event.setEventDescription(description);
        event.setEventDate(date);
        event.setEventTime(time);
        event.setEventDuration(duration);

        this.calenderRepository.save(event);

        return event.getId();
    }

    @PostMapping(path = "/delete")
    public @ResponseBody Integer deleteEvent(@RequestParam Integer eventId) {
        this.calenderRepository.deleteById(eventId);

        return eventId;
    }
}