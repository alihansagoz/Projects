public class Person {
    private final String name;
    private final String surname;
    private String country;
    private final String uniqueId;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Person(String uniqueId, String name, String surname, String country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }
}