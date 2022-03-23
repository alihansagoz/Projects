public class Director extends Artist{
    private final String Agent;

    public Director(String  uniqueId, String name, String surname, String country, String agent) {
        super(uniqueId,name, surname, country);
        Agent=agent;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname() + " "+ Agent;
    }
}