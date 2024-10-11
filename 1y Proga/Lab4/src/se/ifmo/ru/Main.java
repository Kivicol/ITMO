package se.ifmo.ru;

import se.ifmo.ru.objects.*;
import se.ifmo.ru.places.*;

public class Main {
    public static void main(String[] args) {

        System.out.println();
        Place room = new Place("Локация1");
        room.setType(Places.ROOM);
        Place ladder = new Place("Локация2");
        ladder.setType(Places.LADDER);
        Place ceiling = new Place("Локация3");
        ceiling.setType(Places.CEILING);
        Place door = new Place("Локация4");
        door.setType(Places.DOOR);
        Place shelves = new Place("Локация5");
        shelves.setType(Places.SHELVES);
        shelves.newWoahPlace();

        System.out.println("----------------------------------------------------------------------");

        //анонимный класс
        StartLife newBeginning = new StartLife(){
            @Override
            public void newStartForLife() {
                System.out.println("Да будет свет!");
            }
        };

        newBeginning.newStartForLife();
        Things friends = new Things("Друзья");
        Things korotyshki = new Things("Группа коротышек");
        Things neznayka = new Things("Незнайка", 100);
        neznayka.checkHP();
        Things pipes = new Things("Трубы");
        Things lamp = new Things("Лампочка");
        Things inhabitants = new Things("Обитатели");
        Things clothes = new Things("Вещи");
        Things kozlik = new Things("Козлик");
        Things.yavaute udivlenie = neznayka.new yavaute();

        System.out.println("----------------------------------------------------------------------");

        friends.chracteristic(Condition.DOES);
        friends.action(Actions.GETDOWN);
        System.out.println("(" + ladder.getType().getName() + ")");
        friends.chracteristic(Condition.DOES);
        friends.action(Actions.ENTERMLTPL);
        System.out.println("(" + door.getType().getName() + ")");
        friends.chracteristic(Condition.DOES);
        friends.action(Actions.ENTER);
        System.out.println("(" + room.getType().getName() + ")");

        System.out.println("----------------------------------------------------------------------");

        neznayka.chracteristic(Condition.LOOKRND);
        Things.impress.impression();
        udivlenie.zhostkiyaut();
        room.placeCondition(neznayka.reactiontothis(Reaction.LIKEKATALAZHKA));

        System.out.println("----------------------------------------------------------------------");

        room.whatsgoing();
        korotyshki.chracteristic(Condition.DOES);
        korotyshki.action(Actions.LYING);
        shelves.whatstheplace();
        pipes.chracteristic(Condition.DOES);
        pipes.action(Actions.STRETCH);
        room.whatstheplace();
        lamp.chracteristic(Condition.DOES);
        lamp.action(Actions.HANGSOLE);
        ceiling.whatstheplace();
        room.placeCondition("грязно и тесно");
        inhabitants.chracteristic(Condition.DOES);
        inhabitants.action(Actions.USEFREEDOM);
        inhabitants.chracteristic(Condition.DOES);
        inhabitants.action(10,Actions.BAKEPOTATO, Actions.BOILSOUP, Actions.FRYPANCAKES, Actions.HANGPANCAKES);
        pipes.entCondition(clothes.getName().toLowerCase());
        System.out.println(Actions.HANGMANY.getName().toLowerCase());

        System.out.println("----------------------------------------------------------------------");

        room.placeCondition(neznayka.reactiontothis(45, Reaction.SMELLY));
        neznayka.chracteristic(Condition.EXISTS);
        neznayka.action(Actions.GOTBAD);
        neznayka.entCondition("перехватило дыхание и помутилось в глазах\n");
        neznayka.damageGet(AbsEnt.getOdor());
        neznayka.checkHP();
        kozlik.chracteristic(Condition.DOES);
        kozlik.action(Actions.CATCH);
        neznayka.toWhom();
        kozlik.chracteristic(Condition.DOES);
        kozlik.action(Actions.REASSURE);
        neznayka.toWhom();
        neznayka.restoreHP();
        neznayka.checkHP();
    }
}

/*
Спустившись по грязной деревянной лестнице на два этажа вниз,
наши друзья вошли в дверь и очутились в огромной комнате с низким, прогнувшимся,
закопченным потолком. Первое впечатление у Незнайки было, будто он снова попал в каталажку.
Такие же складские полки с лежавшими на них коротышками, такая же чугунная печь с
длинными, тянувшимися через все помещение трубами, такая же тусклая лампочка
под потолком. Вся разница заключалась в том, что здесь было гораздо грязней и тесней.
Полки были не из пластмассы, как в каталажке, а из грубых, почерневших, неотесанных
деревянных досок, и стояли они так тесно, что между лежавшими на них коротышками, казалось,
трудно было просунуть палец. В отличие от арестованных, которые томились в каталажке, здешние
обитатели пользовались гораздо большей свободой. Каждый здесь считал себя вправе делать все,
что ему приходило в голову. Многие не только пекли картошку в золе, но и варили в жестянках
из-под консервов похлебку, жарили какие-то длинные, бесформенные коржи из теста, развешивая
их на горячих жестяных трубах. На этих же трубах висели, вперемежку с коржами, чьи-то носки,
сохнущее после стирки белье, изорванное до последней степени тряпье, бывшее когда-то одеждой,
даже чьи-то ботинки. От всего этого жарящегося, варящегося, пекущегося, сохнущего
и просто чадящего в помещении стоял такой удушливый запах, что у Незнайки перехватило дыхание
и помутилось в глазах. Почувствовав головокружение, он зашатался и принялся хвататься
руками за стенку. Видя, что Незнайка неожиданно побледнел, Козлик подхватил его под руки и сказал,
что это ничего, что это у него с непривычки и постепенно пройдет.
-- Старайся только не дышать носом. Дыши ртом, -- советовал Козлик.
Незнайка старательно зажал пальцами нос. Понемногу он отдышался и пришел в себя.
-- Теперь тебе надо полежать, и все будет хорошо, -- утешал его Козлик.

                                                                   <<<karlson.name.length()-1>>>   ?????
 println(karlson.getName().subString(откакогозначения, конечныйиндекс karlson.getName().length()-1))
исключение на присвоения действия (второе другое)
сделать "открытие" двери и оформить исключения а-ля включение/выключение
 */