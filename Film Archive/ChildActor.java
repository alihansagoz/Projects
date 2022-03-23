public class ChildActor extends Performer{
    private final String Age;

    public ChildActor(String uniqueId, String name, String surname, String country, String age) {
        super(uniqueId, name, surname, country);
        Age=age;
    }
    public String toString() {
        return this.getName() + " " + this.getSurname() + " "+ Age;
    }
}

