package pl.coderslab.finalProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.finalProject.categories.Category;
import pl.coderslab.finalProject.categories.CategoryRepository;

@Component
public class AppInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public AppInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void run(String... args) {
        Category filmfest = new Category(1L, "festiwal filmowy");
        Category musicfest = new Category(2L, "festiwal muzyczny");
        Category concert = new Category(3L, "koncert");

        categoryRepository.save(filmfest);
        categoryRepository.save(musicfest);
        categoryRepository.save(concert);
    }
}
