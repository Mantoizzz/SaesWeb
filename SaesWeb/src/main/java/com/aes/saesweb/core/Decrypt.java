package com.aes.saesweb.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Mantozi
 */
@Service
@Slf4j
public class Decrypt {
    public static int[][] decrypt(int[][] plainText, int[][] secretKey) throws Exception {
        int[][] key1 = KeyExpand.getNextKey(secretKey, 1);
        int[][] key2 = KeyExpand.getNextKey(key1, 2);
        int[][] res = Round1.reverseProcess(plainText, key2);
        res = Round2.reverseProcess(res, key1);
        res = Round3.reverseProcess(res, secretKey);
        return res;
    }

}
