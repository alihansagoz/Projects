import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TVSeries extends Film {

    private final String genreofseries;
    private final ArrayList<Writer2> writers;
    private final String startdate;
    private final String enddate;
    private final String numberofseasons;
    private final String numberofepisodes;

    public TVSeries(String filmid, String filmtitle, String language, ArrayList<Director> directors, String runtime, String country, ArrayList<Performer> cast, String genreofseries, ArrayList<Writer2> writers, String startdate, String enddate, String numberofseasons, String numberofepisodes) {
        super(filmid, filmtitle, language, directors, runtime, country, cast);
        this.genreofseries = genreofseries;
        this.writers = writers;
        this.startdate = startdate;
        this.enddate = enddate;
        this.numberofseasons = numberofseasons;
        this.numberofepisodes = numberofepisodes;
    }
    public String toString() {
        StringBuilder strwriters = new StringBuilder();
        for (Writer2 writer : writers) {
            strwriters.append(writer.getName()).append(" ").append(writer.getSurname()).append(", ");
        }
        StringBuilder strdirectors = new StringBuilder();
        for (Director director : getDirectors()) {
            strdirectors.append(director.getName()).append(" ").append(director.getSurname()).append(", ");
        }
        StringBuilder strstars = new StringBuilder();
        for (Performer star : getCast()) {
            strstars.append(star.getName()).append(" ").append(star.getSurname()).append(", ");
        }
        strdirectors.setLength(strdirectors.length() - 2);
        strstars.setLength(strstars.length() - 2);
        strwriters.setLength(strwriters.length() - 2);
        List list = TakinRating();
        String releaseStart = startdate.substring(startdate.length() - 4);
        String releaseEnd = enddate.substring(enddate.length() - 4);
        if (list.get(1).equals(0)) {
            return getFilmtitle() + " (" + releaseStart + "-" + releaseEnd + ")"
                    + "\n" + numberofseasons + " seasons, " + numberofepisodes + " episodes" +
                    "\n" + genreofseries.replace(",",", ") + "\n"
                    + "Writers: " + strwriters + "\n"
                    + "Directors: " + strdirectors + "\n"
                    + "Stars: " + strstars + "\n"
                    + "Awaiting for votes";
        } else {
            return getFilmtitle() + " (" + releaseStart + "-" + releaseEnd + ")"
                    + "\n" + numberofseasons + " seasons, " + numberofepisodes + " episodes" +
                    "\n" + genreofseries.replace(",",", ") + "\n"
                    + "Writers: " + strwriters + "\n"
                    + "Directors: " + strdirectors + "\n"
                    + "Stars: " + strstars + "\n"
                    + "Ratings: " + new DecimalFormat("##.#").format(list.get(0)) + "/10" + " from " + list.get(1) + " users";
        }
    }
    public String toStringbyRate() {
        List list = TakinRating();
        return getFilmtitle() + " ("  + startdate.substring(startdate.length()-4) + "-"+enddate.substring(enddate.length()-4) +")"+" Ratings: " + new DecimalFormat("##.#").format(list.get(0)) + "/"+ "10" + " from " + list.get(1) + " users" +"\n" ;

    }
    public String toStringtv() {
        return  getFilmtitle() + " ("  + startdate.substring(startdate.length()-4) + "-"+enddate.substring(enddate.length()-4) +")" + "\n" + numberofseasons + " seasons and " + numberofepisodes + " episodes";
    }

    public String toStringbyCountry(){
        return "Film title: "+getFilmtitle() +"\n"
                +getRuntime() + " min" + "\n"
                +"Language: " + getLanguage() + "\n" ;

    }



}
