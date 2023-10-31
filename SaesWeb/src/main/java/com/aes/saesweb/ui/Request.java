package com.aes.saesweb.ui;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Request implements Serializable {
    @JsonProperty
    String text;

    @JsonProperty
    String key;

    @JsonProperty
    Boolean isEncrypt;
}
