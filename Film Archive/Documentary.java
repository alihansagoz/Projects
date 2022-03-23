import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Documentary extends Film{
    private final String releasedate;

    public Documentary(String filmid, String filmtitle, String language, ArrayList<Director> directors, String runtime, String country, ArrayList<Performer> cast, String releasedate) {
        super(filmid, filmtitle, language, directors, runtime, country, cast);
        this.releasedate = releasedate;
    }

    public String toString() {
        StringBuilder strdirectors= new StringBuilder();
        for (Director director : getDirectors()){
            strdirectors.append(director.getName()).append(" ").append(director.getSurname()).append(", ");
        }
        StringBuilder strstars= new StringBuilder();
        for (Performer star : getCast()){
            strstars.append(star.getName()).append(" ").append(star.getSurname()).append(", ");
        }

        strdirectors.setLength(strdirectors.length() - 2);
        strstars.setLength(strstars.length() - 2);

        List list = TakinRating();
        String releaseYear = releasedate.substring(releasedate.length() - 4);
        if (list.get(1).equals(0)) {
            return getFilmtitle() + " ("  + releaseYear +")" + "\n"
                    +"Directors: " + strdirectors  +"\n"
                    +"Stars: " +  strstars +"\n"
                    + "Awaiting for votes";
        }
        return getFilmtitle() + " ("  + releaseYear +")"+ "\n"
                +"Directors: " + strdirectors  +"\n"
                +"Stars: " +  strstars +"\n"
                +"Ratings: " + new DecimalFormat("##.#").format(list.get(0)) + "/"+ "10" + " from " + list.get(1) + " users" ;

    }
    public String toStringbyRate() {
        List list = TakinRating();
        return getFilmtitle() + " ("  + releasedate.substring(releasedate.length()-4) +")" +" " +"Ratings: " + new DecimalFormat("##.#").format(list.get(0))+ "/"+ "10" + " from " + list.get(1) + " users" +"\n";

    }

    public String toStringbyCountry(){
        return "Film title: " + getFilmtitle() + "\n"
                +getRuntime() + " min" + "\n"
                +"Language: " + getLanguage() + "\n" ;
    }
}

