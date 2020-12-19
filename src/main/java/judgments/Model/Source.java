package judgments.Model;

import java.net.URL;

public class Source {

    private Code code;
    private URL judgmentUrl;
    private String judgmentId;
    private String publisher;
    private String reviser;
    private String publicationDate;

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

}
