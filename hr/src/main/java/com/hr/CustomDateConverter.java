package com.hr;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

//날짜 컨버터
public class CustomDateConverter extends PropertyEditorSupport {

    private final DateFormat dateFormat;

    public CustomDateConverter(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Date parsedDate = dateFormat.parse(text);
            setValue(parsedDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format", e);
        }
    }
}
