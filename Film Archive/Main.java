import java.io.*;
import java.util.*;

public class Main {
    public static void write(String var0, File var1) throws IOException {
        FileWriter var2 = new FileWriter(var1,true);
        var2.append(var0);
        var2.close();
    }
    public static String findFilmType(Film film){
        if (film instanceof FeatureFilm){
            return "FeatureFilm";
        }
        else if (film instanceof ShortFilm){
            return "ShortFilm";
        }
        else if (film instanceof TVSeries){
            return "TVSeries";
        }
        else if (film instanceof Documentary){
            return "Documentary";
        }
        return null;
    }
    public static Film SearchFilm(String uniqueid, ArrayList<Film> k){
        for (Film film :k){
            if (uniqueid.equals(film.getFilmid())){
                return film;
            }
        }
        return null;
    }
    public static Person Searchpeople(String uniqueid, ArrayList<Person> k){
        for (Person person :k){
            if (uniqueid.equals(person.getUniqueId())){
                return person;
            }
        }
        return null;
    }
    public static ArrayList<Director> FindDirectors(String Peoples,ArrayList<Person> persons) {
        String[] Peoplearray = Peoples.split(",");
        ArrayList<Director> directors = new ArrayList<>();
        for (String s : Peoplearray) {
            Person person = Searchpeople(s, persons);
            if (person instanceof Director) {
                directors.add((Director) person);
            }
            else {
                return null;
            }
        }
        return directors;
    }
    public static ArrayList<Writer2> FindWriters(String Peoples,ArrayList<Person> persons) {
        String[] Peoplearray = Peoples.split(",");
        ArrayList<Writer2> writers = new ArrayList<>();
        for (String s : Peoplearray) {
            Person person = Searchpeople(s, persons);
            if (person instanceof Writer2) {
                writers.add((Writer2) person);
            }
            else{
                return null;
            }
        }
        return writers;
    }
    public static ArrayList<Performer> FindPerformer(String Peoples,ArrayList<Person> persons) {
        String[] Peoplearray = Peoples.split(",");
        ArrayList<Performer> performers = new ArrayList<>();
        for (String s : Peoplearray) {
            Person person = Searchpeople(s, persons);
            if (person instanceof Performer) {
                performers.add((Performer) person);
            }
            else{
                return null;
            }
        }
        return performers;
    }
    public static void main(String[] args) throws IOException {
        File filewriter = new File(args[3]);
        FileWriter var2 = new FileWriter(filewriter);
        var2.close();

        ArrayList<Film> AllFilms = new ArrayList<>();
        ArrayList<Person> AllPerson = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileReader(args[0]));
            while (scanner.hasNextLine()) {
                String people_information = scanner.nextLine();
                String[] Peopleinfo = people_information.split("\t");
                switch (Peopleinfo[0]) {
                    case "Actor:" -> AllPerson.add(new Actor(Peopleinfo[1], Peopleinfo[2], Peopleinfo[3], Peopleinfo[4], Peopleinfo[5]));
                    case "ChildActor:" -> AllPerson.add(new ChildActor(Peopleinfo[1], Peopleinfo[2], Peopleinfo[3], Peopleinfo[4], Peopleinfo[5]));
                    case "StuntPerformer:" -> AllPerson.add(new StuntPerformer(Peopleinfo[1], Peopleinfo[2], Peopleinfo[3], Peopleinfo[4], Peopleinfo[5], Peopleinfo[6]));
                    case "User:" -> AllPerson.add(new User(Peopleinfo[1], Peopleinfo[2], Peopleinfo[3], Peopleinfo[4]));
                    case "Writer:" -> AllPerson.add( new Writer2(Peopleinfo[1], Peopleinfo[2], Peopleinfo[3], Peopleinfo[4], Peopleinfo[5]) ) ;
                    case "Director:" -> AllPerson.add(new Director(Peopleinfo[1], Peopleinfo[2], Peopleinfo[3], Peopleinfo[4], Peopleinfo[5]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner2 = new Scanner(new FileReader(args[1]));
            while (scanner2.hasNextLine()) {
                String film_information = scanner2.nextLine();
                String[] Filminfo = film_information.split("\t");
                ArrayList<Director> Directors= FindDirectors(Filminfo[4],AllPerson);
                ArrayList<Performer> Cast= FindPerformer(Filminfo[7],AllPerson);

                switch (Filminfo[0]) {
                    case "FeatureFilm:" -> {
                        ArrayList<Writer2> Writers = FindWriters(Filminfo[10],AllPerson);
                        if (Directors!=null && Cast!=null && Writers!=null){
                            AllFilms.add(new FeatureFilm(Filminfo[1], Filminfo[2], Filminfo[3], Directors,Filminfo[5], Filminfo[6], Cast, Filminfo[8], Filminfo[9], Writers,Filminfo[11]));
                        }
                        else {
                            System.out.println("Command Failed");
                        }
                    }
                    case "ShortFilm:" -> {
                        ArrayList<Writer2> Writers = FindWriters(Filminfo[10],AllPerson);
                        if (Directors!=null && Cast!=null && Writers!=null && Integer.parseInt(Filminfo[5])<=40){
                            AllFilms.add(new ShortFilm(Filminfo[1], Filminfo[2], Filminfo[3], Directors, Filminfo[5], Filminfo[6], Cast, Filminfo[8], Filminfo[9],Writers));
                        }
                        else {
                            System.out.println("Command Failed");
                        }
                    }
                    case "Documentary:" -> {
                        if (Directors!=null && Cast!=null){
                            AllFilms.add(new Documentary(Filminfo[1], Filminfo[2], Filminfo[3], Directors, Filminfo[5], Filminfo[6], Cast, Filminfo[8]));
                        }
                        else {
                            System.out.println("Command Failed");
                        }
                    }
                    case "TVSeries:" -> {
                        ArrayList<Writer2> Writers = FindWriters(Filminfo[9],AllPerson);
                        if (Directors!=null && Cast!=null && Writers!=null){
                            AllFilms.add(new TVSeries(Filminfo[1], Filminfo[2], Filminfo[3], Directors, Filminfo[5], Filminfo[6], Cast, Filminfo[8],Writers,Filminfo[10],Filminfo[11],Filminfo[12],Filminfo[13]));
                        }
                        else {
                            System.out.println("Command Failed");
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner3 = new Scanner(new FileReader(args[2]));
            while (scanner3.hasNextLine()) {
                String command_information = scanner3.nextLine();
                String[] Commandinfos = command_information.split("\t");
                write(command_information + "\n",filewriter);
                write("\n",filewriter);
                if ("RATE".equals(Commandinfos[0])) {
                    String userid = Commandinfos[1];
                    String filmid = Commandinfos[2];
                    Person user1 = Searchpeople(userid, AllPerson);
                    Film film = SearchFilm(filmid, AllFilms);
                    if (film != null && user1 instanceof User && Integer.parseInt(Commandinfos[3]) <= 10) {
                        User user = (User) user1;
                        int rate = Integer.parseInt(Commandinfos[3]);
                        if (user.getFilmRates().get(film) == null) {
                            write("Film rated successfully\n",filewriter);
                            write("Film type: " + findFilmType(film) + "\n",filewriter);
                            write("Film title: " + film.getFilmtitle() + "\n",filewriter);
                            user.letsPoint(film, rate);
                            film.letsPoint(user, rate);
                        } else {
                            write("This film was earlier rated\n",filewriter);
                        }
                    } else {
                        write("Command Failed\n",filewriter);
                        write("User ID: " + Commandinfos[1] + "\n",filewriter);
                        write("Film ID: " + Commandinfos[2] + "\n",filewriter);
                    }
                } else if ("ADD".equals(Commandinfos[0])) {
                    String filmid = Commandinfos[2];
                    String filmtitle = Commandinfos[3];
                    String filmlanguage = Commandinfos[4];
                    String directorids = Commandinfos[5];
                    String filmruntime = Commandinfos[6];
                    String filmcountry = Commandinfos[7];
                    String castids = Commandinfos[8];
                    String genre = Commandinfos[9];
                    String releasedate = Commandinfos[10];
                    String writerids = Commandinfos[11];
                    String budget = Commandinfos[12];
                    ArrayList<Director> directors = FindDirectors(directorids, AllPerson);
                    ArrayList<Performer> cast = FindPerformer(castids, AllPerson);
                    ArrayList<Writer2> writers = FindWriters(writerids, AllPerson);
                    if ((SearchFilm(filmid, AllFilms) == null) && (directors) != null && (cast) != null && (writers) != null) {
                        write("FeatureFilm added successfully\n",filewriter);
                        AllFilms.add(new FeatureFilm(filmid, filmtitle, filmlanguage, directors, filmruntime, filmcountry, cast, genre, releasedate, writers, budget));
                    } else {
                        write("Command Failed\n",filewriter);
                    }
                    write("Film ID: " + filmid + "\n",filewriter);
                    write("Film title: " + filmtitle+ "\n",filewriter);
                } else if ("VIEWFILM".equals(Commandinfos[0])) {

                    String filmid = Commandinfos[1];
                    Film film = SearchFilm(filmid, AllFilms);

                    if (film != null) {
                        write(film + "\n",filewriter);
                    } else {
                        write("Command Failed\n",filewriter);
                        write("Film ID: " + filmid +"\n",filewriter);
                    }
                } else if ("EDIT".equals(Commandinfos[0])) {
                    User user = (User) Searchpeople(Commandinfos[2], AllPerson);
                    Film film = SearchFilm(Commandinfos[3], AllFilms);
                    if (user != null && film != null && Integer.parseInt(Commandinfos[4]) <= 10) {
                        if (user.getFilmRates().get(film) != null) {
                            write("New ratings done successfully\n",filewriter);
                            write("Film title: " + film.getFilmtitle() + "\n",filewriter);
                            write("Your rating: " + Commandinfos[4] +"\n",filewriter);
                            user.getFilmRates().replace(film, Integer.valueOf(Commandinfos[4]));
                            film.getFilmRates().replace(user, Integer.valueOf(Commandinfos[4]));
                        } else {
                            write("Command Failed\n",filewriter);
                            write("User ID: " + Commandinfos[2] + "\n" , filewriter);
                            write("Film ID: " + Commandinfos[3]+ "\n",filewriter);
                        }
                    } else {
                        write("Command Failed\n",filewriter);
                        write("User ID: " + Commandinfos[2] +"\n",filewriter);
                        write("Film ID: " + Commandinfos[3] +"\n",filewriter);
                    }
                } else if ("REMOVE".equals(Commandinfos[0])) {
                    User user = (User) Searchpeople(Commandinfos[2], AllPerson);
                    Film film = SearchFilm(Commandinfos[3], AllFilms);
                    if (user != null && film != null && user.getFilmRates().get(film) != null) {
                        write("Your film rating was removed successfully\n",filewriter);
                        write("Film title: " + film.getFilmtitle() +"\n",filewriter);
                        user.getFilmRates().remove(film);
                        film.getFilmRates().remove(user);
                    } else {
                        write("Command Failed\n" ,filewriter);
                        write("User ID: " + Commandinfos[2]+"\n",filewriter);
                        write("Film ID: " + Commandinfos[3]+"\n" ,filewriter);
                    }
                } else if ("LIST".equals(Commandinfos[0])) {
                    if ("USER".equals(Commandinfos[1])) {
                        Person user1 = Searchpeople(Commandinfos[2], AllPerson);
                        if (user1 instanceof User) {
                            User user = (User) user1;
                            for (Film film : user.getFilmRates().keySet()) {
                                int rate = user.getFilmRates().get(film);
                                write(film.getFilmtitle() + ": " + rate+"\n",filewriter);
                            }
                        } else {
                            write("Command Failed\n",filewriter);
                            write("User ID: " + Commandinfos[2] +"\n",filewriter);
                        }
                    } else if ("ARTISTS".equals(Commandinfos[1])) {
                        String country = Commandinfos[3];
                        write("Directors:"+"\n",filewriter);
                        StringBuilder strdirector = new StringBuilder();
                        StringBuilder strwriter = new StringBuilder();
                        StringBuilder stractor = new StringBuilder();
                        StringBuilder strchildactor= new StringBuilder();
                        StringBuilder strstuntperformer = new StringBuilder();
                        for (Person director : AllPerson) {
                            if (director.getCountry().equals(country) && director instanceof Director) {
                                Director director1 = (Director) director;
                                strdirector.append(director1);
                                strdirector.append("\n");
                            }
                        }
                        if (strdirector.toString().equals("")){
                            write("No result"+"\n",filewriter);
                            write("\n",filewriter);
                        }
                        else {
                            write(strdirector+"\n",filewriter);
                        }
                        write("Writers:"+"\n",filewriter);
                        for (Person writer : AllPerson) {
                            if (writer.getCountry().equals(country) && writer instanceof Writer2) {
                                Writer2 writer1 = (Writer2) writer;
                                strwriter.append(writer1);
                                strwriter.append("\n");
                            }
                        }
                        if (strwriter.toString().equals("")){
                            write("No result"+"\n",filewriter);
                            write("\n",filewriter);
                        }
                        else {
                            write(strwriter+"\n",filewriter);
                        }
                        write("Actors:"+"\n",filewriter);
                        for (Person actor : AllPerson) {
                            if (actor.getCountry().equals(country) && actor instanceof Actor) {
                                Actor actor1 = (Actor) actor;
                                stractor.append(actor1);
                                stractor.append("\n");
                            }
                        }
                        if (stractor.toString().equals("")){
                            write("No result"+"\n",filewriter);
                            write("\n",filewriter);
                        }
                        else {
                            write(stractor+"\n",filewriter);
                        }
                        write("ChildActors:"+"\n",filewriter);
                        for (Person childactor : AllPerson) {
                            if (childactor.getCountry().equals(country) && childactor instanceof ChildActor) {
                                ChildActor childactor1 = (ChildActor) childactor;
                                strchildactor.append(childactor1);
                                strchildactor.append("\n");
                            }
                        }
                        if (strchildactor.toString().equals("")){
                            write("No result"+"\n",filewriter);
                            write("\n",filewriter);
                        }
                        else {
                            write(strchildactor+"\n",filewriter);
                        }
                        write("StuntPerformers:"+"\n",filewriter);
                        for (Person stuntperformers : AllPerson) {
                            if (stuntperformers.getCountry().equals(country) && stuntperformers instanceof StuntPerformer) {
                                StuntPerformer stuntperformer1 = (StuntPerformer) stuntperformers;
                                strstuntperformer.append(stuntperformer1);
                                strstuntperformer.append("\n");
                            }
                        }
                        if (strstuntperformer.toString().equals("")){
                            write("No result"+"\n",filewriter);
                            write("\n",filewriter);
                        }
                        else {
                            write(strstuntperformer+"\n",filewriter);
                        }
                    }
                    else if ("FEATUREFILMS".equals(Commandinfos[1])) {
                        int year = Integer.parseInt(Commandinfos[3]);
                        StringBuilder strfeaturefilms = new StringBuilder();
                        for (Film film :AllFilms){
                            if (film instanceof FeatureFilm) {
                                FeatureFilm featureFilm = (FeatureFilm) film;
                                int featureyear = Integer.parseInt(featureFilm.getReleaseDate().substring(featureFilm.getReleaseDate().length() - 4));
                                if ("BEFORE".equals(Commandinfos[2])) {
                                    if (featureyear < year) {
                                        strfeaturefilms.append(featureFilm.toStringbyfeature());
                                        strfeaturefilms.append("\n");
                                    }
                                }
                                else if ("AFTER".equals(Commandinfos[2])) {
                                    if (featureyear >= year) {
                                        strfeaturefilms.append(featureFilm.toStringbyfeature());
                                        strfeaturefilms.append("\n");
                                    }
                                }
                            }
                        }
                        if (strfeaturefilms.toString().equals("")){
                            write("No result"+"\n",filewriter);
                        }
                        else {
                            write(strfeaturefilms.toString(),filewriter);
                        }
                    }
                    else if ("FILM".equals(Commandinfos[1])) {
                        for (Film film: AllFilms){
                            if (film instanceof TVSeries){
                                TVSeries tvSeries = (TVSeries) film;
                                write(tvSeries.toStringtv()+"\n",filewriter);
                                write("\n",filewriter);
                            }
                        }
                    }
                    else if ("FILMS".equals(Commandinfos[1])) {
                        if ("DEGREE".equals(Commandinfos[4])){
                            for (Film film : AllFilms) {
                                film.setAvarageRating(film.getAvarageRating());
                            }
                            AllFilms.sort(Comparator.comparingDouble(Film::getAvarageRating).reversed());
                            write("FeatureFilm:"+"\n",filewriter);
                            StringBuilder strfeaturefilm = new StringBuilder();
                            for (Film film : AllFilms) {
                                if (film instanceof FeatureFilm) {
                                    strfeaturefilm.append(((FeatureFilm) film).toStringbyRate());
                                }
                            }
                            if (strfeaturefilm.toString().equals("")){
                                write("No result"+"\n",filewriter);
                            }
                            else {
                                write(strfeaturefilm+"\n",filewriter);
                            }
                            write("ShortFilm:"+"\n",filewriter);
                            StringBuilder strshortfilm = new StringBuilder();
                            for (Film film : AllFilms) {
                                if (film instanceof ShortFilm) {
                                    strshortfilm.append(((ShortFilm) film).toStringbyRate());
                                }
                            }
                            if (strshortfilm.toString().equals("")){
                                write("No result"+"\n",filewriter);
                            }
                            else {
                                write(strshortfilm+"\n",filewriter);
                            }
                            write("Documentary:"+"\n",filewriter);
                            StringBuilder strdocumentary = new StringBuilder();
                            for (Film film : AllFilms) {
                                if (film instanceof Documentary) {
                                    strdocumentary.append(((Documentary) film).toStringbyRate());
                                }
                            }
                            if (strdocumentary.toString().equals("")){
                                write("No result"+"\n",filewriter);
                            }
                            else {
                                write(strdocumentary+"\n",filewriter);
                            }

                            write("TVSeries:"+"\n",filewriter);
                            StringBuilder strtvseries = new StringBuilder();
                            for (Film film : AllFilms) {
                                if (film instanceof TVSeries) {
                                    strtvseries.append(((TVSeries) film).toStringbyRate());
                                }
                            }
                            if (strtvseries.toString().equals("")){
                                write("No result"+"\n",filewriter);
                            }
                            else {
                                write(strtvseries+"\n",filewriter);
                            }
                        }
                        else if ("COUNTRY".equals(Commandinfos[3])){
                            StringBuilder strcountry = new StringBuilder();
                            for (Film film: AllFilms){
                                if (film.getCountry().equals(Commandinfos[4])){
                                    strcountry.append(film.toStringbyCountry());
                                    strcountry.append("\n");
                                }
                            }
                            if (strcountry.toString().equals("")){
                                write("No result",filewriter);
                            }
                            else {
                                write(strcountry + "",filewriter);
                            }
                        }
                    }
                }
                BufferedReader reader = new BufferedReader(new FileReader(args[3]));
                String line, lastLine = null;
                while ((line = reader.readLine()) != null) {
                    lastLine = line;
                }
                if (lastLine == null || !lastLine.isEmpty()) {
                    write("\n", filewriter);
                }
                write("-----------------------------------------------------------------------------------------------------\n",filewriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}