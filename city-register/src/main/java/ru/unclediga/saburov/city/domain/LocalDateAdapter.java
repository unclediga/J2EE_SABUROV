package ru.unclediga.saburov.city.domain;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
