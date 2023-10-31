package com.aes.saesweb.core;

import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 密钥扩展
 */
@Service
public class KeyExpand {

    /**
     * 获得下一个密钥(注意密钥前8位在第一列，后8位在第二列)
     *
     * @param secretKey secretKey
     * @return result
     */
    public static int[][] getNextKey(int[][] secretKey, int round) throws Exception {

        if (round != 1 && round != 2) {
            throw new Exception("Invalid Round Parameter");
        }

        int[] rightKey = new int[]{secretKey[0][1], secretKey[1][1]};
        int[] tempRightKey = Arrays.copyOf(rightKey, rightKey.length);
        int[] leftKey = new int[]{secretKey[0][0], secretKey[1][0]};
        int[] gResult = KeyProcess.GFunction(tempRightKey, round);
        int[] nextKeyLeft = KeyProcess.XNOR(leftKey, gResult);
        int[] nextKeyRight = KeyProcess.XNOR(nextKeyLeft, rightKey);
        return new int[][]{
                {nextKeyLeft[0], nextKeyRight[0]},
                {nextKeyLeft[1], nextKeyRight[1]}
        };
    }
}
