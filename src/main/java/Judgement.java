import com.google.gson.annotations.SerializedName;
import data.JudgementAttributes.*;

import java.util.Calendar;
import java.util.List;

public class Judgement {

    private int id;
    private CourtType courtType;
    private List<CourtCase> courtCases;
    private JudgementType judgmentType;
    private List<Judge> judges;
    private Source source;
    private List<String> courtReporters;
    private String decision;
    private String summary;
    private String textContent;
    private List<String> legalBases;
    private List<ReferencedRegulation> referencedRegulations;
    private List<String> keywords;
    private List<ReferencedCourtCase> referencedCourtCases;
    private String receiptDate;
    private String meansOfAppeal;
    private String judgmentResult;
    private List<String> lowerCourtJudgments;
    private PersonnelType personnelType; //Sąd najwyższy
    @SerializedName("division.id")
    private int divisionID;
    private List<Chamber> chambers;
    private List<DissentingOpinion> dissentingOpinions; //Trybunał konstytucyjny

    public int getId() {
        return id;
    }

    public CourtType getCourtType() {
        return courtType;
    }

    public List<CourtCase> getCourtCases() {
        return courtCases;
    }

    public JudgementType getJudgmentType() {
        return judgmentType;
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public Source getSource() {
        return source;
    }

    public List<String> getCourtReporters() {
        return courtReporters;
    }

    public String getDecision() {
        return decision;
    }

    public String getSummary() {
        return summary;
    }

    public String getTextContent() {
        return textContent;
    }

    public List<String> getLegalBases() {
        return legalBases;
    }

    public List<ReferencedRegulation> getReferencedRegulations() {
        return referencedRegulations;
    }

    public String getMeansOfAppeal() {
        return meansOfAppeal;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public List<ReferencedCourtCase> getReferencedCourtCases() {
        return referencedCourtCases;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public String getJudgmentResult() {
        return judgmentResult;
    }

    public List<String> getLowerCourtJudgments() {
        return lowerCourtJudgments;
    }

    public PersonnelType getPersonnelType() {
        return personnelType;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public List<Chamber> getChambers() {
        return chambers;
    }

    public List<DissentingOpinion> getDissentingOpinions() {
        return dissentingOpinions;
    }

    public String toString(){
        return this.getCourtCases().get(0).caseNumber;
    }

}
