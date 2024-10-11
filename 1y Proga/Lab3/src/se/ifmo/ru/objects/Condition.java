package se.ifmo.ru.objects;

public enum Condition {
    LOOKRND("осматривается"),
    EXISTS(""),
    DOES("выполняет действие");

    private String name;

    Condition(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}