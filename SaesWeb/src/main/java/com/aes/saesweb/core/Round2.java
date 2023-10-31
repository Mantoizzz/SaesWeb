package com.aes.saesweb.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Mantozi
 */
@Service
@Slf4j
public class Round2 {

    public static int[][] process(int[][] text, int[][] secretKey) {
        Process.substitution(text);
        Process.rowMove(text);
        text = Process.columnMix(text);
        text = Process.XNOR(text, secretKey);
        return text;
    }

    public static int[][] reverseProcess(int[][] text, int[][] secretKey) {
        Process.rowMove(text);
        Process.reverseSubstitution(text);
        text = Process.XNOR(text, secretKey);
        return Process.reverseColumnMix(text);
    }

}
