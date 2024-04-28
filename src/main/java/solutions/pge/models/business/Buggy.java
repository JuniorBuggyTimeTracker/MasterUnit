package solutions.pge.models.business;

import java.util.UUID;

record Buggy(
        UUID uuid,

        String rfidChipId,
        Club club,
        BuggyColor color
        ) {
    public Buggy(String rfidChipId, Club club, BuggyColor color){
        this(UUID.randomUUID(), rfidChipId, club, color);
    }
}
