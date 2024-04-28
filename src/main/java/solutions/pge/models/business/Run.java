package solutions.pge.models.business;

import java.time.OffsetDateTime;

public record Run(
        Integer sequentialNumber,
        OffsetDateTime start
) {

}
