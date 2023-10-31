package com.aes.saesweb.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Mantozi
 */
@Service
@Slf4j
public class Round1 {

    public static int[][] process(int[][] plainText, int[][] secretKey) {
        return Process.XNOR(plainText, secretKey);
    }


    public static int[][] reverseProcess(int[][] text, int[][] secretKey) {
        return Process.XNOR(text, secretKey);
    }
}
