package se.ifmo.ru.objects;

public interface HonestReaction {
    String reactiontothis(Reaction react);
    String reactiontothis(Integer damage, Reaction react);
}
