package com.skt.tracking.util;

public class Strings
{
    public static boolean isNotEmpty(CharSequence str)
    {
        return !isEmpty(str);
    }

    public static boolean isEmpty(CharSequence str)
    {
        return str == null || str.length() == 0;
    }
}
