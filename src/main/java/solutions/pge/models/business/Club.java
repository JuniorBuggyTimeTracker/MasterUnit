package solutions.pge.models.business;

public record Club(
        String name,
        String city,
        String address
) {
    public Club(String name, String city){
        this(name, city, "");
    }
}
