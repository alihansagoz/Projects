import java.util.*;

public class Film{
    private final String Filmid;
    private final String Filmtitle;
    private final String Language;
    private final ArrayList<Director> Directors;
    private final String Runtime;
    private String Country;
    private final ArrayList<Performer> Cast;
    private double AvarageRating = 0;
    private final LinkedHashMap<User, Integer> FilmRates = new LinkedHashMap<>();

    public double getAvarageRating() {
        if (TakinRating().get(0)!=null) {
            if (TakinRating().get(0) instanceof Integer){
                return (int) (TakinRating().get(0));
            }
            else {
                return (double) TakinRating().get(0);
            }
        }
        else {
            return 0;
        }
    }

    public void setAvarageRating(double avarageRating) {
        AvarageRating = avarageRating;
    }


    public List TakinRating(){

        Iterator<User> users = FilmRates.keySet().iterator();
        double z=0;
        int k=0;
        while (users.hasNext()) {
            User users1 = users.next();
            if (users1.getFilmRates().get(this)!=null){
                k++;
                z+=users1.getFilmRates().get(this);
            }
        }
        if (k==0){
            return Arrays.asList(0, k);
        }
        double l = z/k;

        if (l%1==0){
            return Arrays.asList((int) l, k);
        }
        return Arrays.asList(l, k);
    }

    public String getFilmid() {
        return Filmid;
    }

    public String getFilmtitle() {
        return Filmtitle;
    }

    public String getLanguage() {
        return Language;
    }

    public ArrayList<Director> getDirectors() {
        return Directors;
    }

    public String  getRuntime() {
        return Runtime;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public ArrayList<Performer> getCast() {
        return Cast;
    }

    public LinkedHashMap<User, Integer> getFilmRates() {
        return FilmRates;
    }

    public Film(String filmid, String filmtitle, String language, ArrayList<Director> directors, String  runtime, String country, ArrayList<Performer> cast) {
            Filmid = filmid;
            Filmtitle = filmtitle;
            Language = language;
            Directors = directors;
            Runtime = runtime;
            Country = country;
            Cast = cast;}
    public void letsPoint(User x, Integer rate) {
        FilmRates.put(x, rate);
    }

    @Override
    public String toString() {
        return "Film{" +
                "Filmid='" + Filmid + '\'' +
                ", Filmtitle='" + Filmtitle + '\'' +
                ", Language='" + Language + '\'' +
                ", Directors=" + Directors +
                ", Runtime='" + Runtime + '\'' +
                ", Country='" + Country + '\'' +
                ", Cast=" + Cast +
                ", FilmRates=" + FilmRates +
                '}';
    }

    public String toStringbyCountry(){
        return null;
    }

}


