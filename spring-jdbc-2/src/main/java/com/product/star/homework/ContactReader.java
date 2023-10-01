package com.product.star.homework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactReader {

    private static final String SEPARATOR = ",";

    public List<Contact> readFromFile(Path filePath) {
        List<Contact> contactList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(filePath)) {

            contactList = stream.map(ContactReader::parseContact)
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            System.out.println("Ошибка чтения файла");
            ex.printStackTrace();
        }

        return contactList;
    }

    private static Contact parseContact(String line) {
        String[] tokens = line.split(SEPARATOR);
        return new Contact(tokens[0], tokens[1], tokens[2], tokens[3]);
    }
}
