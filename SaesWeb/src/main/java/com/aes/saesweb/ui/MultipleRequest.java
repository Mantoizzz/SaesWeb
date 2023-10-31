package com.aes.saesweb.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleRequest {

    String text;

    String keyA;

    String keyB;

    String keyC;

    Boolean isEncrypt;

}

