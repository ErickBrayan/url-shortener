package com.eb2.urlshorteningservice.entities;

import com.eb2.urlshorteningservice.validator.constraint.Url;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestDto(

        @NotNull(message = "url can not be null")
        @NotEmpty(message = "url can not be empty")
        @Url
        String url
) {
}
