package judgments.Attributes;

public enum JudgmentType {
    DECISION,
    RESOLUTION,
    SENTENCE,
    REGULATION,
    REASONS;

    public String toString(){
        switch (this){
            case DECISION:
                return "Postanowienie";
            case RESOLUTION:
                return "Uchwała";
            case SENTENCE:
                return "Wyrok";
            case REGULATION:
                return "Zarządzenie";
            case REASONS:
                return "Uzasadnienie";
            default:
                return "Błędne dane";
        }
    }
}
