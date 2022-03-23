public class Writer2 extends Artist{
    private final String WritingStyle;

    public Writer2(String uniqueId, String name, String surname, String country, String writingStyle) {
        super(uniqueId, name, surname, country);
        WritingStyle=writingStyle;
    }

    public String toString() {
        return this.getName() + " " + this.getSurname() + " "+ WritingStyle;
    }



}


