package se.ifmo.ru.objects;


public enum Actions {
    GETDOWN("Спуск"),
    ENTER("Очутиться"),
    ENTERMLTPL("Войти"),
    GETTOSOLE("Попасть"),
    STRETCH("Тянуться"),
    HANGSOLE("Висеть"),
    HANGMANY(" Висят (много)"),
    STAND("Стоять"),
    USEFREEDOM("Пользоваться"),
    BAKEPOTATO("Печь картошку"),
    BOILSOUP("Варить похлёбку"),
    FRYPANCAKES("Жарить коржи"),
    HANGPANCAKES("Развешивать коржи"),
    GOTBAD("Поплохело"),
    CATCH("Подхватить"),
    REASSURE("Успокоить"),
    LYING("Лежать");

    private String name;
    Actions(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
