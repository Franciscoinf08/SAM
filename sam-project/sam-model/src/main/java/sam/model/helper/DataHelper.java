package sam.model.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHelper {

    public static String dataFormat1(String unformattedData) {
        String formatedData;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        try {
            Date newDate = inputFormat.parse(unformattedData);
            inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            formatedData = inputFormat.format(newDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return formatedData;
    }
}
