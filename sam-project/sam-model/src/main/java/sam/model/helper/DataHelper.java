package sam.model.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DataHelper {

    public static boolean verificarProximidade(LocalDate data1, LocalDate data2, long meses) {
        return ChronoUnit.MONTHS.between(data1, data2) < meses;
    }

    public static boolean verificarProximidadeAgora(LocalDate data, long meses) {
        return verificarProximidade(LocalDate.now(), data, meses);
    }

    public static String dataFormat1(String unformattedData) {
        String formatedData;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newDate = inputFormat.parse(unformattedData);
            inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            formatedData = inputFormat.format(newDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return formatedData;
    }
}