public class Authors {
    private final String id;
    private String name;
    private String university;
    private String department;
    private String email;
    private final Articles[] articles = new Articles[5];
    public Articles[] getArticles() {
        return articles;
    }
    public String getId() {
        return id;
    }
    public Authors(String id) {
        this.id = id;
    }
    public Authors(String id, String name, String university, String department, String email) {
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
    }
    public String showArticles(){
        StringBuilder str = new StringBuilder();
        for (Articles articles2: articles){
            if (articles2!=null) {
                str.append(articles2.toString()).append("\n");
            }
        }
        return str.toString();
    }
    public String showInformation(){
        if (name==null){
            return id;
        }
        else {
            return id + "\t" +  name+ "\t" +  university + "\t" + department + "\t" + email ;
        }
    }
    public boolean controlArticles(Articles y) {
        for (Articles articles2 : this.articles){
            if (articles2!=null){
                if (articles2.getPaperid().equals(y.getPaperid())){
                    return true;
                }
            }
        }
        return false;
    }
}
