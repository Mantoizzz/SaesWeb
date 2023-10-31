package com.aes.saesweb.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Mantozi
 */
@Service
@Slf4j
public class Encrypt {

    public static int[][] encrypt(int[][] plainText, int[][] secretKey) throws Exception {
        int[][] key1 = KeyExpand.getNextKey(secretKey, 1);
        int[][] key2 = KeyExpand.getNextKey(key1, 2);
        int[][] res = Round1.process(plainText, secretKey);
        res = Round2.process(res, key1);
        res = Round3.process(res, key2);
        return res;
    }

}
