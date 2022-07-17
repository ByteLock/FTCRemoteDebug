package com.bytelock.RemoteDebug.formatting;

public class FormatObject {

    /**
     * The actual object itself
     */
    public Object fobject;

    /**
     * The index in the string
     */
    public int findex;

    /**
     * The format string=
     */
    public String fformat;

    public FormatObject(Object o, int index, String format) {
        fobject = o;
        findex = index;
        fformat = format;
    }

}
