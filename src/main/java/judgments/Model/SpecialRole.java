package judgments.Model;

public enum SpecialRole {
    PRESIDING_JUDGE,
    REPORTING_JUDGE,
    REASONS_FOR_JUDGMENT_AUTHOR;

    public String toString(){
        switch (this){
            case PRESIDING_JUDGE:
                return "Przewodniczący Składu Sędziowskiego";
            case REPORTING_JUDGE:
                return "Sędzia Sprawodawca";
            case REASONS_FOR_JUDGMENT_AUTHOR:
                return "Autor uzasadnienia";
            default:
                return "Błędne dane";
        }
    }
}
