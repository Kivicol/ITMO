package command.commands;

import java.io.Serial;

public class CountLessThanDistanceCom implements BasicCommand{

    /**
     * Command 'Count_less_than_distance'
     * Counts the number of elements whose distance is less than the specified one
     */

    @Serial
    private final static long serialVersionUID = 2L;

    private float distance;

    public CountLessThanDistanceCom(float dist) {
        this.distance = dist;
    }
}
