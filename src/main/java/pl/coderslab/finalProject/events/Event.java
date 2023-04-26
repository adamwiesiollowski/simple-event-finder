package pl.coderslab.finalProject.events;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.finalProject.categories.Category;
import pl.coderslab.finalProject.places.Place;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "wpisz nazwę wydarzenia")
    private String name;

    @URL(message = "podaj poprawny adres url")
    private String link;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBeginning;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    @ManyToOne
    private Place place;

    @ManyToOne
    private Category category;

    public String getDateRange() {
        if (dateEnd != null) {
            return dateBeginning.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " do " + dateEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return dateBeginning.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }

    public String getDayOfWeek() {
        if (dateEnd != null) {
            String startDay = dateBeginning.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("pl"));
            String endDay = dateEnd.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("pl"));
            return startDay + " - " + endDay;
        } else {
            return dateBeginning.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("pl"));
        }
    }

    @AssertTrue
    public boolean getIsDateBeginningBeforeDateEnd() {
        if (dateEnd == null) {
            return true;
        }
        if (dateBeginning == null) {
            return false;
        }
        return dateBeginning.isBefore(dateEnd);
    }

}