package judgments.Attributes;

public class ReferencedRegulation {

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    String journalTitle;

    public String getJournalTitle() {
        return journalTitle;
    }

    public int getJournalYear() {
        return journalYear;
    }

    public int getJournalNo() {
        return journalNo;
    }

    public int getJournalEntry() {
        return journalEntry;
    }

    public String getText() {
        return text;
    }

    int journalYear;
    int journalNo;
    int journalEntry;

    public void setText(String text) {
        this.text = text;
    }

    String text;

}
