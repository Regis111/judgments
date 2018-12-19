package judgments.Attributes;

import java.net.URL;

public class Source {

    Code code;
    URL judgmentUrl;
    String judgmentId;
    String publisher;
    String reviser;
    String publicationDate;

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Code getCode() {
        return code;
    }

    public URL getJudgmentUrl() {
        return judgmentUrl;
    }

    public String getJudgmentId() {
        return judgmentId;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getReviser() {
        return reviser;
    }
}
