package pl.coderslab.finalProject.places;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.finalProject.events.EventRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class PlaceAdminController {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    public PlaceAdminController(EventRepository eventRepository, PlaceRepository placeRepository) {
        this.eventRepository = eventRepository;
        this.placeRepository = placeRepository;
    }

    // creating place

    @GetMapping("/addplace")
    public String showAddPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        return "admin/places/placeAddForm";
    }

    @PostMapping("/addplace")
    public String savePlace(@Valid Place place, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("places", placeRepository.findAll());
            return "admin/places/placeAddForm";
        }
        placeRepository.save(place);
        redirectAttributes.addFlashAttribute("message", "Dodano nowe miejsce!");
        return "redirect:/admin/addplace";
    }

    // editing and deleting places

    @GetMapping("/placelist")
    public String showPlaceList(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 50;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Place> placesPage = placeRepository.findAllPlacesOrderedByCityMyMethod(pageable);
        List<Place> places = placesPage.getContent();
        model.addAttribute("places", places);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", placesPage.getTotalPages());
        return "admin/places/placeList";
    }

    @GetMapping("/editplace")
    public String showEditPlaceForm(@RequestParam Long id, Model model) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid place id: " + id));
        model.addAttribute("place", place);
        return "admin/places/placeEditForm";
    }

    @PostMapping("/editplace")
    public String editPlace(@ModelAttribute("place") @Valid Place place, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/places/placeEditForm";
        }
        placeRepository.save(place);
        redirectAttributes.addFlashAttribute("message", "Edycja miejsca powiodła się!");
        return "redirect:/admin/placelist";
    }

    @GetMapping("/deleteplace")
    public String deletePlace(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        boolean existsEventsForPlace = eventRepository.existsByPlaceId(id);
        if (existsEventsForPlace) {
            redirectAttributes.addFlashAttribute("message", "Nie można usunąć miejsca powiązanego z wydarzeniem!");
            return "redirect:/admin/placelist";
        }
        placeRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Miejsce zostało usunięte.");
        return "redirect:/admin/placelist";
    }
}
