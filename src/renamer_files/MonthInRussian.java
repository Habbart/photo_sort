package renamer_files;

public enum MonthInRussian {

    JANUARY("Январь"),
    FEBRUARY("Февраль"),
    MARCH("Март"),
    APRIL("Апрель"),
    MAY("Май"),
    JUNE("Июнь"),
    JULY("Июль"),
    AUGUST("Август"),
    SEPTEMBER("Сентябрь"),
    OCTOBER("Октябрь"),
    NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь");



    private final String fieldName;

    MonthInRussian(String monthName) {
        this.fieldName = monthName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
