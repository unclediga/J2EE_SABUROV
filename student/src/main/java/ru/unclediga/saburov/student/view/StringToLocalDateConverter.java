package ru.unclediga.saburov.student.view;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter extends StdConverter<String, LocalDate> {
    private final static String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public LocalDate convert(String s) {
        return s == null || s.trim().isEmpty() ? null :
                LocalDate.parse(s, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
