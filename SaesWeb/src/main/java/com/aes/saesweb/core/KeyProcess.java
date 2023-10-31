package com.aes.saesweb.core;

import com.aes.saesweb.Constants;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mantozi
 */
@Slf4j
public class KeyProcess {

    /**
     * G函数
     *
     * @param key   key
     * @param round round
     * @return result
     */
    public static int[] GFunction(int[] key, int round) {
        swap(key);
        KeyProcess.substitutionForKey(key);
        XNOR(key, round);
        return key;
    }

    /**
     * 与其他key异或
     *
     * @param key1 key1
     * @param key2 key2
     */
    public static int[] XNOR(int[] key1, int[] key2) {
        int[] res = new int[2];
        for (int i = 0; i < 2; i++) {
            res[i] = key1[i] ^ key2[i];
        }
        return res;
    }

    /**
     * 与轮常数进行异或
     *
     * @param key   key
     * @param round round
     */
    private static void XNOR(int[] key, int round) {
        if (round == 1) {
            for (int i = 0; i < key.length; i++) {
                key[i] ^= Constants.RoundConstants1[i];
            }
        } else {
            for (int i = 0; i < key.length; i++) {
                key[i] ^= Constants.RoundConstants2[i];
            }
        }
    }

    /**
     * 适用密钥扩展的S盒替换
     *
     * @param key key
     */
    private static void substitutionForKey(int[] key) {
        for (int i = 0; i < 2; i++) {
            int cur = key[i];
            int front = (cur & 0b1100) >> 2;
            int rear = cur & 0b0011;
            key[i] = Constants.SubstitutionBox[front][rear];
        }
    }

    /**
     * 左循环移位
     *
     * @param key key
     */
    private static void swap(int[] key) {
        int temp = key[0];
        key[0] = key[1];
        key[1] = temp;
    }
}
