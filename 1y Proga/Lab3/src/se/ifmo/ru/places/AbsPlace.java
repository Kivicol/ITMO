package se.ifmo.ru.places;

import se.ifmo.ru.objects.Reaction;

public abstract class AbsPlace implements IntPlace{
    public String place_name;
    public String type_name;
    public String condition;

    @Override
    public int hashCode() {
        return super.hashCode()+this.getPlace().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public String toString() {
        return "Место " + this.getPlace();
    }

    public String getPlace() {
        return place_name;
    }

}
