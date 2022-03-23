public class StuntPerformer extends Performer{
    private String Height;
    private String RealIds;

    public StuntPerformer(String uniqueId, String name, String surname, String country, String height,String realIds) {
        super(uniqueId, name, surname, country);
        Height=height;
        RealIds=realIds;
    }
    public String toString() {
        return this.getName() + " " + this.getSurname() + " "+ Height +" cm";
    }
}

