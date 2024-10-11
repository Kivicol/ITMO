package command.utility;

import com.google.gson.*;
import com.google.gson.stream.*;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeAdapter extends TypeAdapter<ZonedDateTime> {

    /**
     * Class for adapting pesky "ZonedDateTime" variables for "Builders"
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    public void write(JsonWriter out, ZonedDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(formatter.format(value));
        }
    }

    @Override
    public ZonedDateTime read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String date = in.nextString();
        return ZonedDateTime.parse(date, formatter);
    }
}
