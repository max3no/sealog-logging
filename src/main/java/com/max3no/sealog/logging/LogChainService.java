package com.max3no.sealog.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max3no.sealog.model.LogEntry;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicReference;
/*
 * Sealog Logging Framework
 * Created by Max3no - https://github.com/max3no/sealog-logging
 * Licensed under MIT
 */

@Service
public class LogChainService {

    private static final Logger logger = LoggerFactory.getLogger(LogChainService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AtomicReference<String> lastHash = new AtomicReference<>("GENESIS");
    private final String logFilePath;

    public LogChainService(String logFilePath) {
        this.logFilePath = logFilePath;
        initializeHashFromFile(); // load from file
    }

    private void initializeHashFromFile() {
        File file = new File(logFilePath);
        if (!file.exists() || file.length() == 0) {
            lastHash.set("GENESIS");
            return;
        }
        try {
            String lastLine = readLastLine(file);
            if (lastLine != null) {
                LogEntry lastEntry = objectMapper.readValue(lastLine, LogEntry.class);
                lastHash.set(lastEntry.getHash());
            }
        } catch (Exception e) {
            logger.warn("Failed to read last hash from file: {}", logFilePath, e);
            lastHash.set("GENESIS");
        }
    }

    private String readLastLine(File file) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long fileLength = raf.length();
            if (fileLength == 0) {
                return null;
            }

            long pointer = fileLength - 1;
            StringBuilder line = new StringBuilder();

            while (pointer >= 0) {
                raf.seek(pointer);
                int readByte = raf.readByte();

                if (readByte == '\n' && pointer != fileLength - 1) {
                    break;
                }
                line.append((char) readByte);
                pointer--;
            }

            return line.reverse().toString();
        }
    }

    public void log(String timestamp, String source, String message, String ip, String hostname) {
        try {
            String prevHash = lastHash.get();

            LogEntry entry = new LogEntry();
            entry.setTimestamp(timestamp);
            entry.setSource(source);
            entry.setMessage(message);
            entry.setIp(ip);
            entry.setHostname(hostname);
            entry.setPrevHash(prevHash);

            String rawLog = objectMapper.writeValueAsString(entry.withoutHash());
            String hash = calculateHash(rawLog);
            entry.setHash(hash);

            // Update hash chain
            lastHash.set(hash);

            // Append to log file
            writeToFile(objectMapper.writeValueAsString(entry));
        } catch (Exception e) {
            logger.error("Failed to log entry", e);
        }
    }

    private void writeToFile(String logLine) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(logLine);
            writer.newLine();
        }
    }

    private String calculateHash(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encodedHash);
    }
}
