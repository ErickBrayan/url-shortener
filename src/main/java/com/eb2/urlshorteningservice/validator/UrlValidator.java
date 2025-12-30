package com.eb2.urlshorteningservice.validator;

import com.eb2.urlshorteningservice.validator.constraint.Url;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator implements ConstraintValidator<Url, String> {


    private static final String REGEX_URL = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";

    private static final Pattern PATTERN = Pattern.compile(REGEX_URL, Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value != null && !value.isEmpty()) {
            Matcher matcher = PATTERN.matcher(value);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
