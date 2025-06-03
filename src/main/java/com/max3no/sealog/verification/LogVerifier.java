package com.max3no.sealog.verification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max3no.sealog.crypto.HashGenerator;
import com.max3no.sealog.model.LogEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogVerifier {

    private final ObjectMapper mapper = new ObjectMapper();

    public boolean verifyLogFile(String logFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String prevHash = null;
            String line;
            List<LogEntry> allEntries = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                LogEntry entry = mapper.readValue(line, LogEntry.class);
                String computedHash = HashGenerator.computeHash(entry.withoutHash());

                if (!computedHash.equals(entry.getHash())) {
                    System.err.println("Hash mismatch at entry: " + entry.getMessage());
                    return false;
                }

                if (prevHash != null && !prevHash.equals(entry.getPrevHash())) {
                    System.err.println("Broken chain before entry: " + entry.getMessage());
                    return false;
                }

                prevHash = entry.getHash();
                allEntries.add(entry);
            }

            System.out.println("Log chain verified successfully. Total entries: " + allEntries.size());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

