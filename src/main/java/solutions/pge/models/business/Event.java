package solutions.pge.models.business;


import java.time.OffsetDateTime;

public record Event(
        String venue,
        Club organizer,
        OffsetDateTime date,
        Integer runs,
        Float trackLength
) {

}
