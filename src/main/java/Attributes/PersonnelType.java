package Attributes;

public enum PersonnelType {
    ONE_PERSON,
    THREE_PERSON,
    FIVE_PERSON,
    SEVEN_PERSON,
    ALL_COURT,
    ALL_CHAMBER,
    JOINED_CHAMBERS;

    public String toString(){
        switch(this){
            case ONE_PERSON:
                return "skład jednoosobowy";
            case THREE_PERSON:
                return "skład trzyosobowy";
            case FIVE_PERSON:
                return "skład siedmioosobowy";
            case SEVEN_PERSON:
                return "skład siedmioosobowy";
            case ALL_COURT:
                return "skład całego Sądu Najwyższego";
            case ALL_CHAMBER:
                return "skład pełnej izby";
            case JOINED_CHAMBERS:
                return "skład połączonych izb";
            default:
                return "Błędne dane";
        }
    }
}
