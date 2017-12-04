package top.tonymochel.main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.swing.JFormattedTextField.AbstractFormatter;
 
@SuppressWarnings("serial")
public class DateLabelFormatter extends AbstractFormatter {
 
    private String datePattern = "yyyy-MM-dd";										// D�finition du partern de la date
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);		// D�finition du format de la date
     
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
 
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
 
}
