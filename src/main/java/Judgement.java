import com.google.gson.annotations.SerializedName;
import data.JudgementAttributes.*;

import java.util.Calendar;
import java.util.List;

public class Judgement {

    private int id;
    private CourtType type;
    private List<String> courtCases;
    private JudgementType judgementType;
    private List<Judge> judges;
    private JudgementSource source;
    private List<String> CourtReporters;
    private String decision;
    private String summary;
    private String textContent;
    private List<String> legalBases;
    private List<ReferencedRegulation> regulations;
    private List<String> keywords;
    private List<ReferencedCourtCase> referencedCourtCases;
    private Calendar receiptDate;
    private String meansOfAppeal;
    private String judgmentResult;
    private List<String> lowerCourtJudgements;
    private PersonnelType personnelType; //Sąd najwyższy
    private String judgementForm;
    @SerializedName("division.id")
    private int DivisionInt;
    List<DissentingOpinion> dissentingOpinions; //Trybunał konstytucyjny



    /*public CourtType getType() {
        return type;
    }

    public void setType(CourtType type) {
        this.type = type;
    }

    public List<CourtCase> getCasesList() {
        return casesList;
    }

    public void setCasesList(List<CourtCase> casesList) {
        this.casesList = casesList;
    }

    public JudgementType getJudgementType() {
        return judgementType;
    }

    public void setJudgementType(JudgementType judgementType) {
        this.judgementType = judgementType;
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public void setJudges(List<Judge> judges) {
        this.judges = judges;
    }

    public JudgementSource getSource() {
        return source;
    }

    public void setSource(JudgementSource source) {
        this.source = source;
    }

    public List<CourtReporter> getReporterList() {
        return reporterList;
    }

    public void setReporterList(List<CourtReporter> reporterList) {
        this.reporterList = reporterList;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public List<String> getLegalBases() {
        return legalBases;
    }

    public void setLegalBases(List<String> legalBases) {
        this.legalBases = legalBases;
    }

    public List<referencedRegulations> getRegulationsList() {
        return regulationsList;
    }

    public void setRegulationsList(List<referencedRegulations> regulationsList) {
        this.regulationsList = regulationsList;
    }
    */
}
