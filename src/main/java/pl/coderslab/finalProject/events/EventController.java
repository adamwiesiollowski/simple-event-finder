package pl.coderslab.finalProject.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

//    @GetMapping("/")
//    public String home() {
//        for (Event event : eventRepository.findAll()) {
//            log.debug("event {}", event.getName());
//        }
//        return "home";
//    }

    @GetMapping("/")
    public String showCurrentEvents(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findFutureOrPresentEventsMyMethod(pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/home/home";
    }

    @GetMapping("/filmfest")
    public String showFilmFest(Model model, @RequestParam(required = false) Long id, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findEventsByCategoryIdMyMethod(id, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/home/home";
    }

    @GetMapping("/musicfest")
    public String showMusicFest(Model model, @RequestParam(required = false) Long id, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findEventsByCategoryIdMyMethod(id, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/home/home";
    }

    @GetMapping("/concerts")
    public String showConcerts(Model model, @RequestParam(required = false) Long id, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findEventsByCategoryIdMyMethod(id, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/home/home";
    }

    @GetMapping("/weekendevents")
    public String showWeekendEvents(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findEventsOnWeekendsMyMethod(pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/home/home";
    }

    @GetMapping("/eventsbyname")
    public String showEventsByName(@RequestParam("name") String name, @RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findEventsByNameMyMethod(name, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/home/home";
    }

    @GetMapping("/eventsbyplace")
    public String showEventsByPlace(@RequestParam("name") String name, @RequestParam("city") String city, @RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findEventsByPlaceNameMyMethod(name, city, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/home/home";
    }

    @GetMapping("/eventsbycity")
    public String showEventsByCity(@RequestParam("city") String city, @RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findEventsByPlaceCityMyMethod(city, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/home/home";
    }

    @GetMapping("/pastevents")
    public String showPastEvents(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findPastEventsMyMethod(pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/past/showPastEventList";
    }

    @GetMapping("/pasteventsbyname")
    public String showPastEventsByName(@RequestParam("name") String name, @RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findPastEventsByNameMyMethod(name, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/past/showPastEventList";
    }

    @GetMapping("/pasteventsbyplace")
    public String showPastEventsByPlace(@RequestParam("name") String name, @RequestParam("city") String city, @RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findPastEventsByPlaceNameMyMethod(name, city, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/past/showPastEventList";
    }

    @GetMapping("/pasteventsbycity")
    public String showPastEventsByCity(@RequestParam("city") String city, @RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 20;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findPastEventsByPlaceCityMyMethod(city, pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "user/past/showPastEventList";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }
}