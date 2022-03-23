import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FeatureFilm extends Film  {

    private final String filmGenre;
    private final String releaseDate;
    private final ArrayList<Writer2> writers;

    public String getReleaseDate() {
        return releaseDate;
    }

    public FeatureFilm(String filmid, String filmtitle, String language, ArrayList<Director> directors, String runtime, String country, ArrayList<Performer> cast, String filmGenre, String releaseDate, ArrayList<Writer2> writers, String  budget) {
        super(filmid, filmtitle, language, directors, runtime, country, cast);
        this.writers=writers;
        this.filmGenre=filmGenre;
        this.releaseDate=releaseDate;
    }

    @Override
    public String toString() {
        StringBuilder strwriters= new StringBuilder();
        for (Writer2 writer : writers){
            strwriters.append(writer.getName()).append(" ").append(writer.getSurname()).append(", ");
        }
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
        strwriters.setLength(strwriters.length() - 2);

        List list = TakinRating();
        String releaseyear = releaseDate.substring(releaseDate.length() - 4);
        if(list.get(1).equals(0)){
            return getFilmtitle() + " ("  + releaseyear + ")"
                    + "\n"+ filmGenre.replace(",",", ") +"\n"
                    +"Writers: " +  strwriters +"\n"
                    +"Directors: " + strdirectors  +"\n"
                    +"Stars: " +  strstars +"\n"
                    + "Awaiting for votes";
        }
        else {
            return getFilmtitle() + " ("  + releaseyear + ")"
                    +"\n"+ filmGenre.replace(",",", ") +"\n"
                    +"Writers: " +  strwriters +"\n"
                    +"Directors: " + strdirectors  +"\n"
                    +"Stars: " +  strstars +"\n"
                    +"Ratings: " + new DecimalFormat("##.#").format(list.get(0))+ "/"+ "10" + " from " + list.get(1) + " users" ;
        }
    }

    public String toStringbyRate() {
        List list = TakinRating();
        return getFilmtitle() + " ("  +releaseDate.substring(releaseDate.length()-4) +") " +"Ratings: " +new DecimalFormat("##.#").format(list.get(0)) + "/"+ "10" + " from " + list.get(1) + " users" + "\n" ;

    }

    public String toStringbyCountry(){
        return "Film title: " + getFilmtitle() + "\n"
                +getRuntime() + " min" + "\n"
                +"Language: " + getLanguage() + "\n";
    }

    public String toStringbyfeature(){
        return "Film title: "+ getFilmtitle() + " ("  +releaseDate.substring(releaseDate.length()-4) +")" +"\n"
                +getRuntime() + " min" + "\n"
                +"Language: " + getLanguage() + "\n";
    }

}
