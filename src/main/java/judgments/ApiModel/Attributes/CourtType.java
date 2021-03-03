package judgments.ApiModel.Attributes;

public enum CourtType {

    COMMON,
    SUPREME,
    PROVINCIAL_ADMINISTRATIVE,
    LEADING_ADMINISTRATIVE,
    CONSTITUTIONAL_TRIBUNAL,
    NATIONAL_APPEAL_CHAMBER;



    public String toString(){
        switch(this){
            case COMMON:
                return "Sąd Powszechny";
            case SUPREME:
                return "Sąd Najwyższy";
            case PROVINCIAL_ADMINISTRATIVE:
                return "Wojewódzki Sąd Administracyjny";
            case LEADING_ADMINISTRATIVE:
                return "Naczelny Sąd Administracyjny";
            case CONSTITUTIONAL_TRIBUNAL:
                return "Trybunał Konstytucyjny";
            case NATIONAL_APPEAL_CHAMBER:
                return "Krajowa Izba Odwoławcza";
            default:
                return "Błędne dane";
        }
    }

}
