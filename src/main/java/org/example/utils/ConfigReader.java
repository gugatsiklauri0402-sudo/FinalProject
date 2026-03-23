package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

// ConfigReader — კლასი, რომელიც კითხულობს მნიშვნელობებს config.properties ფაილიდან
public class ConfigReader {

    // private მეთოდი — კითხულობს კონკრეტულ key-ს properties ფაილიდან
    private static String read(String key) {
        Properties properties = new Properties();
        try {
            // ვხსნით config.properties ფაილს
            InputStream inputStream = Files.newInputStream(Paths.get("config.properties"));

            // ვტვირთავთ ფაილის მონაცემებს properties-ში
            properties.load(inputStream);

        } catch (IOException e) {

            // თუ ფაილი ვერ ჩაიტვირთა — ვბეჭდავთ ერორს
            System.out.println("config.properties file loading error" + e.getMessage());
        }

        // ვაბრუნებთ კონკრეტული key-ის მნიშვნელობას
        return properties.getProperty(key);
    }

    // public მეთოდი — აბრუნებს String მნიშვნელობას config-დან
    public static String get(String key){
        return read(key);
    }

    // public მეთოდი — აბრუნებს Long ტიპის მნიშვნელობას (მაგ: wait time)
    public static Long getLong(String key){

        // String-ს გარდაქმნის Long-ად
        return Long.parseLong(read(key));
    }
}
