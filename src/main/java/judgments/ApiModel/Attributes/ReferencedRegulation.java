package judgments.ApiModel.Attributes;

public class ReferencedRegulation {


    private String journalTitle;
    private String text;
    private int journalYear;
    private int journalNo;
    private int journalEntry;

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


    public void setJournalYear(int journalYear) {
        this.journalYear = journalYear;
    }
}
