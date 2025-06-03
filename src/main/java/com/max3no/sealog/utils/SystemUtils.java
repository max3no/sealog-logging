package com.max3no.sealog.utils;

import java.net.InetAddress;

/*
 * Sealog Logging Framework
 * Created by Max3no - https://github.com/max3no/sealog-logging
 * Licensed under MIT
 */

public class SystemUtils {
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "unknown-host";
        }
    }
}
