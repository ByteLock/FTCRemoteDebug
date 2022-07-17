package com.bytelock.RemoteDebug.formatting;

public class Formatter {

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

    private static final String[] formats = {
            "tstmp",
            "dstmp",
            "nbr",
            "str",
            "flt",
            "dble"
    };

    public static boolean checkFormat(FormatString fstring, Object[] objects) {

        // The first check pass is meant to catch an invalid number of format strings to
        // user given objects

        int nF = 0;
        int nO = objects.length;
        for(int i = 0; i < fstring.getFormatString().length(); i++) {
            if(String.valueOf(fstring.getFormatString().charAt(i)).equals("$")) {
                nF++;
            }
        }

        if(nF != nO) {
            System.out.println("[Formatter] Invalid number of format strings to objects");
            return false;
        }


        // The second pass will traverse the string and find the special character. The formatter
        // will not split the string into different parts, though that might help, it will not
        // help the formatter determine the location of the special character, and determine what
        // the type of format string it is









        // Second check pass is to determine the validity of each object and determine
        // if the given object type matches the one in the format string
//        for(int i = 0; i < s.length; i++) {
//            for(String f : formats) {
//                switch (s[i]) {
//                    case "tstmp":
//                    case"dstmp":
//                        break;
//
//                    case"nbr":
//                        if(objects[i] instanceof Integer) {
//                            continue;
//                        } else {
//                            return false;
//                        }
//
//                    case"str":
//                        if(objects[i] instanceof String) {
//                            continue;
//                        } else {
//                            return false;
//                        }
//
//                    case"flt":
//                        if(objects[i] instanceof Float) {
//                            continue;
//                        } else {
//                            return false;
//                        }
//
//
//                    case"dble":
//                        if(objects[i] instanceof Double) {
//                            continue;
//                        } else {
//                            return false;
//                        }
//                }
//            }
//        }


        return true;
    }


    private static int genInt(Object o) {
        return (int) o;
    }

    private static double genDouble(Object o) {
        return (double) o;
    }

    private static String genString(Object o) {
        return String.valueOf(o);
    }

    private static float genFloat(Object o) {
        return (float) o;
    }
}
