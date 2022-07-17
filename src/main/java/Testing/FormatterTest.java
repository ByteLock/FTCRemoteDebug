package Testing;

import com.bytelock.RemoteDebug.formatting.FormatString;
import com.bytelock.RemoteDebug.formatting.Formatter;

public class FormatterTest {

    public static void main(String[] args) {
        System.out.println("Beginning Formatter Test v1");

        FormatString formatString = new FormatString("Thread Number: $nbr | Thread Name: $string");
        Object[] objects = { 2, "test" };

        System.out.println(Formatter.checkFormat(formatString, objects));

    }
}
