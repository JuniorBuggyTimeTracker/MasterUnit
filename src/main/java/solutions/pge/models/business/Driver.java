package solutions.pge.models.business;

import java.util.UUID;

public record Driver(
        UUID driverId,
        String rfidChipId,
        String firstname,
        String lastname,
        Club club,
        String raceClass,
        String startNumber,
        Boolean guestStarter

) {

    public Driver(String rfidChipId, String firstName, String lastName, Club club,String raceClass,String startNumber, Boolean guestStarter ){
        this(UUID.randomUUID(),rfidChipId, firstName, lastName,club, raceClass, startNumber, guestStarter);
    }
}
