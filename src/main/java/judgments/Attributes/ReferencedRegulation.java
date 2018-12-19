package judgments.Attributes;

public class ReferencedRegulation {


    String journalTitle;
    String text;
    int journalYear;
    int journalNo;
    int journalEntry;

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

    public void setText(String text) {
        this.text = text;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }



}
