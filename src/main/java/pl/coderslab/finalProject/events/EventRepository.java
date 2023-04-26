package pl.coderslab.finalProject.events;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e " +
            "ORDER BY e.dateBeginning DESC, e.dateEnd DESC, e.name ASC")
    Page<Event> findAllEventsMyMethod(Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE e.dateBeginning >= CURRENT_DATE OR e.dateEnd >= CURRENT_DATE " +
            "ORDER BY e.dateBeginning ASC, e.dateEnd ASC, e.name ASC")
    Page<Event> findFutureOrPresentEventsMyMethod(Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE (e.dateBeginning >= CURRENT_DATE OR e.dateEnd >= CURRENT_DATE) " +
            "AND e.category.id = ?1 ORDER BY e.dateBeginning ASC, e.dateEnd ASC, e.name ASC")
    Page<Event> findEventsByCategoryIdMyMethod(Long id, Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE ((DAYOFWEEK(e.dateBeginning) IN (6, 7, 1) AND e.dateBeginning >= CURRENT_DATE) " +
            "OR (DAYOFWEEK(e.dateEnd) IN (6, 7, 1) AND e.dateEnd >= CURRENT_DATE AND e.dateEnd IS NOT NULL) " +
            "OR (e.dateBeginning <= CURRENT_DATE AND (e.dateEnd >= CURRENT_DATE) AND DAYOFWEEK(CURRENT_DATE) IN (6, 7, 1))) " +
            "AND (e.dateBeginning >= CURRENT_DATE OR e.dateEnd IS NOT NULL) " +
            "ORDER BY e.dateBeginning ASC, e.dateEnd ASC, e.name ASC")
    Page<Event> findEventsOnWeekendsMyMethod(Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE e.name=?1 " +
            "AND (e.dateBeginning >= CURRENT_DATE OR e.dateEnd >= CURRENT_DATE) " +
            "ORDER BY e.dateBeginning ASC, e.dateEnd ASC, e.name ASC")
    Page<Event> findEventsByNameMyMethod(String name, Pageable pageable);

    @Query("SELECT e FROM Event e JOIN e.place p " +
            "WHERE p.city=?1 " +
            "AND (e.dateBeginning >= CURRENT_DATE OR e.dateEnd >= CURRENT_DATE) " +
            "ORDER BY e.dateBeginning ASC, e.dateEnd ASC, e.name ASC")
    Page<Event> findEventsByPlaceCityMyMethod(String city, Pageable pageable);

    @Query("SELECT e FROM Event e JOIN e.place p " +
            "WHERE p.name=?1 AND p.city=?2 " +
            "AND (e.dateBeginning >= CURRENT_DATE OR e.dateEnd >= CURRENT_DATE) " +
            "ORDER BY e.dateBeginning ASC, e.dateEnd ASC, e.name ASC")
    Page<Event> findEventsByPlaceNameMyMethod(String name, String city, Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE (e.dateBeginning < CURRENT_DATE AND e.dateEnd IS NULL) " +
            "OR (e.dateBeginning < CURRENT_DATE AND e.dateEnd < CURRENT_DATE) " +
            "ORDER BY e.dateBeginning DESC, e.dateEnd DESC, e.name ASC")
    Page<Event> findPastEventsMyMethod(Pageable pageable);

        @Query("SELECT e FROM Event e " +
            "WHERE e.name=?1 " +
                "AND ((e.dateBeginning < CURRENT_DATE AND e.dateEnd IS NULL) " +
                "OR (e.dateBeginning < CURRENT_DATE AND e.dateEnd < CURRENT_DATE)) " +
            "ORDER BY e.dateBeginning DESC, e.dateEnd DESC, e.name ASC")
    Page<Event> findPastEventsByNameMyMethod(String name, Pageable pageable);

    @Query("SELECT e FROM Event e JOIN e.place p " +
            "WHERE p.city=?1 " +
            "AND ((e.dateBeginning < CURRENT_DATE AND e.dateEnd IS NULL) " +
            "OR (e.dateBeginning < CURRENT_DATE AND e.dateEnd < CURRENT_DATE)) " +
            "ORDER BY e.dateBeginning DESC, e.dateEnd DESC, e.name ASC")
    Page<Event> findPastEventsByPlaceCityMyMethod(String city, Pageable pageable);

    @Query("SELECT e FROM Event e JOIN e.place p " +
            "WHERE p.name=?1 AND p.city=?2 " +
            "AND ((e.dateBeginning < CURRENT_DATE AND e.dateEnd IS NULL) " +
            "OR (e.dateBeginning < CURRENT_DATE AND e.dateEnd < CURRENT_DATE)) " +
            "ORDER BY e.dateBeginning DESC, e.dateEnd DESC, e.name ASC")
    Page<Event> findPastEventsByPlaceNameMyMethod(String name, String city, Pageable pageable);

    @Query("SELECT COUNT(e) > 0 FROM Event e WHERE e.place.id = ?1")
    boolean existsByPlaceId(Long placeId);

    @Query("SELECT COUNT(e) > 0 FROM Event e WHERE e.category.id = ?1")
    boolean existsByCategoryId(Long categoryId);

}