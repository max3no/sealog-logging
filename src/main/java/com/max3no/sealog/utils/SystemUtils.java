package com.max3no.sealog.utils;

import java.net.InetAddress;

public class SystemUtils {
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "unknown-host";
        }
    }
}
