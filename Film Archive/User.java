import java.util.HashMap;
import java.util.LinkedHashMap;

public class User extends Person{
    private LinkedHashMap<Film, Integer> FilmRates = new LinkedHashMap<Film, Integer>();
    public User(String uniqueId, String name, String surname, String country) {
        super(uniqueId,name, surname, country);
    }
    public HashMap<Film, Integer> getFilmRates() {
        return FilmRates;
    }

    public void letsPoint(Film x,Integer rate) {
        FilmRates.put(x, rate);

    }
}
