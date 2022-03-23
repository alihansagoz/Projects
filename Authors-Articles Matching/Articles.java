public class Articles implements Comparable<Articles>{
    private final String paperid;
    private String name;
    private String publisherName;
    private String publishYear;
    @Override
    public String toString() {
        return paperid + "\t" +  name+ "\t" +  publisherName + "\t" + publishYear ;
    }
    public Articles(String paperid) {
        this.paperid = paperid;
    }
    public String getPaperid() {
        return paperid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }
    public Articles(String paperid, String name, String publisherName, String publishYear) {
        this.paperid = paperid;
        this.name = name;
        this.publisherName = publisherName;
        this.publishYear = publishYear;
    }
    public String showPaperid(){
        return paperid;
    }
    @Override
    public int compareTo(Articles o) {
        return toString().compareTo(o.toString());
    }
}
