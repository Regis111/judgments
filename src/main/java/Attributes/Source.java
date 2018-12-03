package Attributes;

import Attributes.Code;

import java.net.URL;

public class Source {
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

    Code code;
    URL judgmentUrl;
    String judgmentId;
    String publisher;
    String reviser;
    String publicationDate;
}
