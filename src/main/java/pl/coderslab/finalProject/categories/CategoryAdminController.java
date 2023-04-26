package pl.coderslab.finalProject.categories;

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
public class CategoryAdminController {
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    public CategoryAdminController(CategoryRepository categoryRepository, EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    // creating category

    @GetMapping("/addcategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categories/categoryAddForm";
    }

    @PostMapping("/addcategory")
    public String saveCategory(@Valid Category category, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/categories/categoryAddForm";
        }
        categoryRepository.save(category);
        redirectAttributes.addFlashAttribute("message", "Dodano nową kategorię!");
        return "redirect:/admin/addcategory";
    }

    // editing and deleting categories

    @GetMapping("/categorylist")
    public String showCategories(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 50;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Category> categoriesPage = categoryRepository.findNewCategoriesOrderedByNameMyMethod(pageable);
        List<Category> categories = categoriesPage.getContent();
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoriesPage.getTotalPages());
        return "admin/categories/categoryList";
    }

    @GetMapping("/editcategory")
    public String showEditCategoryForm(@RequestParam Long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));
        model.addAttribute("category", category);
        return "admin/categories/categoryEditForm";
    }

    @PostMapping("/editcategory")
    public String editCategory(@ModelAttribute("category") @Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/categories/categoryEditForm";
        }
        categoryRepository.save(category);
        redirectAttributes.addFlashAttribute("message", "Edycja kategorii powiodła się!");
        return "redirect:/admin/categorylist";
    }

    @GetMapping("/deletecategory")
    public String deleteCategory(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        boolean existsEventsForCategory = eventRepository.existsByCategoryId(id);
        if (existsEventsForCategory) {
            redirectAttributes.addFlashAttribute("message", "Nie można usunąć kategorii powiązanej z wydarzeniem!");
            return "redirect:/admin/categorylist";
        }
        categoryRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Kategoria została usunięta.");
        return "redirect:/admin/categorylist";
    }

}
