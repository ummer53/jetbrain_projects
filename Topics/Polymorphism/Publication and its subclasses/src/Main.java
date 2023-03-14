class Publication {

    private String title;

    public Publication(String title) {
        this.title = title;
    }

    public final String getInfo() {
        // write your code here
        String info=this.getType();
        info=info+this.getDetails();
        return info;
    }

    public String getType() {
        return "Publication: ";
    }

    public String getDetails() {
        return this.title;
    }

}

class Newspaper extends Publication {

    private String source;

    public Newspaper(String title, String source) {
        super(title);
        this.source = source;
    }

    // write your code here
    public String getType() {
        return "Newspaper (source - "+this.source+"): ";
    }

    public String getDetails() {

        return super.getDetails();
    }

}

class Article extends Publication {

    private String author;

    public Article(String title, String author) {
        super(title);
        this.author = author;
    }

    // write your code here
    public String getType() {
        return "Article (author - "+this.author+"): ";
    }

    public String getDetails() {

        return super.getDetails();
    }

}

class Announcement extends Publication {

    private int daysToExpire;

    public Announcement(String title, int daysToExpire) {
        super(title);
        this.daysToExpire = daysToExpire;
    }

    // write your code here
    public String getType() {
        return "Announcement (days to expire - "+this.daysToExpire+"): ";
    }

    public String getDetails() {

        return super.getDetails();
    }

}