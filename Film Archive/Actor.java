public class Actor extends Performer{
    private final String Height;

    public Actor(String uniqueId, String name, String surname, String country, String height) {
        super(uniqueId, name, surname, country);
        Height=height;
    }

    public String toString() {
        return this.getName() + " " + this.getSurname() + " "+ Height + " cm";
    }
}
