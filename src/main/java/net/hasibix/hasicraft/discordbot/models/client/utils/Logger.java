package net.hasibix.hasicraft.discordbot.models.client.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;
import javax.annotation.Nullable;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class Logger {
    @Nullable String logsFolder;
    public static class Color {
        // Reset
        public static final String RESET = "\033[0m";  // Text Reset
     
        // Regular Colors
        public static final String BLACK = "\033[0;30m";   // BLACK
        public static final String RED = "\033[0;31m";     // RED
        public static final String GREEN = "\033[0;32m";   // GREEN
        public static final String YELLOW = "\033[0;33m";  // YELLOW
        public static final String BLUE = "\033[0;34m";    // BLUE
        public static final String PURPLE = "\033[0;35m";  // PURPLE
        public static final String CYAN = "\033[0;36m";    // CYAN
        public static final String WHITE = "\033[0;37m";   // WHITE
     
        // Bold
        public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
        public static final String RED_BOLD = "\033[1;31m";    // RED
        public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
        public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
        public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
     
        // Underline
        public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
        public static final String RED_UNDERLINED = "\033[4;31m";    // RED
        public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
        public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
        public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
        public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
        public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
        public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE
     
        // Background
        public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
        public static final String RED_BACKGROUND = "\033[41m";    // RED
        public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
        public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
        public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
        public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
        public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
        public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
     
        // High Intensity
        public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
        public static final String RED_BRIGHT = "\033[0;91m";    // RED
        public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
        public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
        public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
        public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
        public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
     
        // Bold High Intensity
        public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
        public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
     
        // High Intensity backgrounds
        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
        public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
        public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
        public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
        public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    }

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public Logger (@Nullable String logsFolder) {
        if(logsFolder != null) {
            this.logsFolder = logsFolder;
        } else {
            this.logsFolder = null;
        }
    }

    void WriteLog(String text, String logsFolder, String type, LocalDateTime now) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        try {
            Files.createDirectories(Paths.get(logsFolder));
            File myObj = new File(logsFolder + type + "." + dtf.format(now) + ".log");
            if (myObj.createNewFile()) {
                FileWriter writer = new FileWriter(logsFolder + type + "." + dtf.format(now) + ".log");
                writer.write(text);
                writer.close();
            } else {
                return;
            }
          } catch (IOException e) {
            System.err.println(Color.CYAN + dtf.format(now) + " | " + Color.RED + "ERR: " +  Color.WHITE + "An error occurred in LOGGER.\n" + e.toString());
          }
    }

    public void Log(String text) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(Color.CYAN + dtf.format(now) + " | " + Color.GREEN + "LOG: " +  Color.WHITE + text);

        if(logsFolder != null) {
            WriteLog(text, logsFolder, "log", now);            
        }
    }

    public void Error(String text) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.err.println(Color.CYAN + dtf.format(now) + " | " + Color.RED + "ERR: " +  Color.WHITE + text);
        if(logsFolder != null) {
            WriteLog(text, logsFolder, "error", now);            
        }
    }

    public void Warning(String text) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(Color.CYAN + dtf.format(now) + " | " + Color.YELLOW + "WARN: " +  Color.WHITE + text);
        if(logsFolder != null) {
            WriteLog(text, logsFolder, "warning", now);            
        }
    }

    public void FatalError(String text) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.err.println(Color.CYAN + dtf.format(now) + " | " + Color.RED_BOLD + "FATAL: " +  Color.WHITE + text);
        if(logsFolder != null) {
            WriteLog(text, logsFolder, "fatalerror", now);            
        }
    }

    //Exeptions
    public void Log(String text, Exception e) {
        String stacktrace = ExceptionUtils.getStackTrace(e);
        Log(text + "\n" + stacktrace);
    }

    public void Error(String text, Exception e) {
        String stacktrace = ExceptionUtils.getStackTrace(e);
        Error(text + "\n" + stacktrace);
    }

    public void Warning(String text, Exception e) {
        String stacktrace = ExceptionUtils.getStackTrace(e);
        Warning(text + "\n" + stacktrace);
    }

    public void FatalError(String text, Exception e) {
        String stacktrace = ExceptionUtils.getStackTrace(e);
        FatalError(text + "\n" + stacktrace);
    }
}
