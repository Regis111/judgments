package data.JudgementAttributes;

import java.net.URL;
import java.util.Calendar;

public class Source {
    private Code code;
    private URL judgmentUrl;
    private String judgmentId;
    private String publisher;
    private String reviser;
    private String publicationDate;

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

    public String getPublicationDate() {
        return publicationDate;
    }

}
