package se.ifmo.ru.exceptions;

import java.io.IOException;

public class WhatsWithHP extends IOException{
    public WhatsWithHP(String message) {
        super(message);
    }
}
