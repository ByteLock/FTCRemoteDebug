package com.bytelock.RemoteDebug.formatting;

public class FormatString {

    /**
     * To properly create a format, you must follow these rules while creating your format string!
     * The main rule to follow is completing your format string, the format strings are listed below
     * 1. Timestamp: $tstmp
     * 2. Datestamp: $dstmp
     * 3. Number: $nbr
     * 4. String: $str
     * 5. Float: $flt
     * 6. Double $dble
     */

    private String fstring;

    public FormatString(String fstring) {
        this.fstring = fstring;
    }

    public String getFormatString() {
        return fstring;
    }

    public void setFormatString(String formatString) {
        this.fstring = formatString;
    }
}
