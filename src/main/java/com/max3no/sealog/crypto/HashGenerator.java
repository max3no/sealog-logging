package com.max3no.sealog.crypto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashGenerator {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String computeHash(Object object) throws Exception {
        String json = mapper.writeValueAsString(object);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(json.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();

        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}

