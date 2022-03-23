import java.io.*;
import java.util.*;
import java.util.Comparator;

public class Main{
    public static void write(String s,File f)throws IOException{
        FileWriter fileWriter = new FileWriter(f,true);
        fileWriter.write(s);
        fileWriter.close();
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Authors> Authors1 = new ArrayList<>();
        ArrayList<Articles> Articles1 = new ArrayList<>();
        File writer = new File("output.txt");
        try {
            Scanner scanner3 = new Scanner(new FileReader(args[0]));
            int y = 0;
            while (scanner3.hasNextLine()) {
                String author_information = scanner3.nextLine();
                String[] array3 = author_information.split(" ");
                if (array3.length==2){
                    Authors1.add(new Authors(array3[1]));
                    y++;
                }
                else {
                    Authors1.add(new Authors(array3[1],array3[2],array3[3],array3[4],array3[5]));
                    int z = 0;
                    if (array3.length>6){
                        for (int x = 6; x<array3.length; x++){
                            Authors1.get(y).getArticles()[z]= new Articles(array3[x]);
                            z++;
                        }
                        y++;
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner1 = new Scanner(new FileReader(args[1]));
            while (scanner1.hasNextLine()) {
                String command_information = scanner1.nextLine();
                String[] array2 = command_information.split(" ");
                switch (array2[0]) {
                    case "read":
                        try (Scanner scanner = new Scanner(new FileReader(array2[1]))) {
                            while (scanner.hasNextLine()) {
                                String article_information = scanner.nextLine();
                                String[] array = article_information.split(" ");
                                Articles1.add(new Articles(array[1],array[2],array[3],array[4]));
                                for (Authors authors : Authors1){
                                    for (Articles articles : authors.getArticles()){
                                        if (articles!=null){
                                            if (articles.showPaperid().equals(array[1])){
                                                articles.setName(array[2]);
                                                articles.setPublisherName(array[3]);
                                                articles.setPublishYear(array[4]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        catch (FileNotFoundException e) {
                            System.out.println("dosya bulunamadı2");
                        }
                        break;
                    case "list":
                        write("----------------------------------------------List---------------------------------------------\n",writer);
                        for (Authors authors: Authors1){
                            write("Author:",writer);
                            write(authors.showInformation()+"\n",writer);
                            write(authors.showArticles()+"\n",writer);
                        }
                        write("----------------------------------------------End----------------------------------------------\n",writer);
                        break;
                    case "completeAll":
                        write("*************************************CompleteAll Successful*************************************\n",writer);
                        for (Articles articles : Articles1){
                            for (Authors authors : Authors1){
                                if (articles.getPaperid().substring(0, 3).equals(authors.getId())){
                                    if (!authors.controlArticles(articles)){
                                        for ( int z=0 ; z<5 ; z++){
                                            if (authors.getArticles()[z]==null){
                                                authors.getArticles()[z]=articles;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "sortedAll":
                        write("*************************************SortedAll Successful*************************************\n",writer);
                        for (Authors authors: Authors1){
                            Arrays.sort(authors.getArticles(), Comparator.nullsFirst(Articles::compareTo));
                        }
                        break;
                    case "del":
                        write("*************************************del Successful*************************************\n",writer);
                        String id23= array2[1];
                        Authors1.removeIf(authors -> authors.getId().equals(id23));
                        break;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("dosya bulunamadı3");
        }
    }
}

