package judgments.ApiModel.Attributes;

public enum Code {
    COMMON_COURT,
    SUPREME_COURT,
    CONSTITUTIONAL_TRIBUNAL,
    NATIONAL_APPEAL_CHAMBER;


    public String toString() {
        switch (this) {
            case COMMON_COURT:
                return "Sąd powszechny";
            case SUPREME_COURT:
                return "Sąd najwyższy";
            case CONSTITUTIONAL_TRIBUNAL:
                return "Trybunał konstytucyjny";
            case NATIONAL_APPEAL_CHAMBER:
                return "Krajowa Izba Odwoławcza";
            default:
                return "Blędne Dane";
        }
    }
}
