package pl.coderslab.finalProject.places;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT p FROM Place p ORDER BY p.city ASC, p.name ASC")
    Page<Place> findAllPlacesOrderedByCityMyMethod(Pageable pageable);

    @Query("SELECT p FROM Place p ORDER BY p.city ASC, p.name ASC")
    List<Place> findAllPlacesOrderedByCityFormMyMethod();

//    @Query("SELECT p FROM Place p WHERE p.name =?1 AND p.city =?2")
//    Optional<Place> findPlacesByNameAndCityMyMethod(String name, String city);

}