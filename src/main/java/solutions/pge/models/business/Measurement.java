package solutions.pge.models.business;

import java.time.OffsetDateTime;

public record Measurement(String id, OffsetDateTime timestamp) {

    public Measurement(String id){
        this(id, OffsetDateTime.now());
    }
}
