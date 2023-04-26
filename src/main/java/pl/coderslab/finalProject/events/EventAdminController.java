package pl.coderslab.finalProject.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.finalProject.categories.CategoryRepository;
import pl.coderslab.finalProject.places.Place;
import pl.coderslab.finalProject.places.PlaceRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class EventAdminController {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final PlaceRepository placeRepository;

    public EventAdminController(EventRepository eventRepository, CategoryRepository categoryRepository, PlaceRepository placeRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.placeRepository = placeRepository;
    }

    // creating event

    @GetMapping("/addevent")
    public String showAddEventForm(Model model) {
        List<Place> places = placeRepository.findAllPlacesOrderedByCityFormMyMethod();
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("places", places);
        model.addAttribute("event", new Event());
        return "admin/events/eventAddForm";
    }

    @PostMapping("/addevent")
    public String saveEvent(@Valid Event event, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("places", placeRepository.findAll());
            return "admin/events/eventAddForm";
        }
        eventRepository.save(event);
        redirectAttributes.addFlashAttribute("message", "Dodano wydarzenie!");
        return "redirect:/admin/addevent";
    }

    // editing and deleting events

    @GetMapping("/eventlist")
    public String showEditList(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 50;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> eventsPage = eventRepository.findAllEventsMyMethod(pageable);
        List<Event> events = eventsPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventsPage.getTotalPages());
        return "admin/events/eventList";
    }

    @GetMapping("/editevent")
    public String showEditForm(@RequestParam Long id, Model model) {
        List<Place> places = placeRepository.findAllPlacesOrderedByCityFormMyMethod();
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("places", places);
        model.addAttribute("event", eventRepository.findById(id));
        return "admin/events/eventEditForm";
    }

    @PostMapping("/editevent")
    public String updateEvent(@Valid Event event, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("places", placeRepository.findAll());
            return "admin/events/eventEditForm";
        }
        eventRepository.save(event);
        redirectAttributes.addFlashAttribute("message", "Edycja wydarzenia powiodła się!");
        return "redirect:/admin/eventlist";
    }

    @GetMapping("/deleteevent")
    public String deleteEvent(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        eventRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Usunięto wydarzenie.");
        return "redirect:/admin/eventlist";
    }

}