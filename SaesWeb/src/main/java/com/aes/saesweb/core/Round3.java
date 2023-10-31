package com.aes.saesweb.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
@Slf4j
public class Round3 {

    public static int[][] process(int[][] text, int[][] secretKey) {
        Process.substitution(text);
        Process.rowMove(text);
        return Process.XNOR(text, secretKey);
    }

    public static int[][] reverseProcess(int[][] text, int[][] secretKey) {
        Process.rowMove(text);
        Process.reverseSubstitution(text);
        text = Process.XNOR(text, secretKey);
        return text;
    }
}
