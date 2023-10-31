package com.aes.saesweb.expand;

import com.aes.saesweb.core.Encrypt;
import com.aes.saesweb.core.Process;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * CBC密码分组链
 */
public class CBC {

    public static String encrypt(List<int[][]> textList, int[][] secretKey) throws Exception {
        Iterator<int[][]> iterator = textList.iterator();
        StringBuilder sb = new StringBuilder();
        int[][] pre = generateIV();
        while (iterator.hasNext()) {
            int[][] cur = Encrypt.encrypt(Process.XNOR(iterator.next(), pre), secretKey);
            pre = Arrays.copyOf(cur, cur.length);
            sb.append(Process.transformToBinary(cur));
        }
        return sb.toString();
    }

    private static int[][] generateIV() {
        int[][] IV = new int[2][2];
        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                IV[i][j] = sr.nextInt() % 16;
            }
        }
        return IV;
    }

}
