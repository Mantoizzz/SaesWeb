package com.aes.saesweb.core;

import com.aes.saesweb.Constants;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mantozi, ChatGPT
 */
@Slf4j
public class Process {


    /**
     * 轮密钥加(逆函数相同)
     *
     * @param text      明文
     * @param secretKey 密钥
     * @return result
     */
    public static int[][] XNOR(int[][] text, int[][] secretKey) {
        int[][] res = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                res[i][j] = text[i][j] ^ secretKey[i][j];
            }
        }

        return res;
    }

    /**
     * 行位移(逆行位移相同)
     *
     * @param text text
     */
    public static void rowMove(int[][] text) {
        int temp = text[1][0];
        text[1][0] = text[1][1];
        text[1][1] = temp;
    }


    /**
     * S盒替换
     *
     * @param text text
     */
    public static void substitution(int[][] text) {
        substitutionHelp(Constants.SubstitutionBox, text);
    }

    /**
     * 逆S盒代替
     *
     * @param text text
     */
    public static void reverseSubstitution(int[][] text) {
        substitutionHelp(Constants.ReverseSubstitutionBox, text);
    }

    /**
     * S盒替换函数
     *
     * @param box  box
     * @param text text
     */
    private static void substitutionHelp(int[][] box, int[][] text) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int cur = text[i][j];
                int front = (cur & 0b1100) >> 2;
                int rear = cur & 0b0011;
                text[i][j] = box[front][rear];
            }
        }
    }

    /**
     * 列混淆
     *
     * @param text text
     * @return result
     */
    public static int[][] columnMix(int[][] text) {
        return columnMixAlgorithm(Constants.ConfusionMatrix, text);
    }

    /**
     * 逆列混淆
     *
     * @param text text
     * @return result
     */
    public static int[][] reverseColumnMix(int[][] text) {
        return columnMixAlgorithm(Constants.ReverseConfusionMatrix, text);
    }

    /**
     * 列混淆计算函数
     *
     * @param matrix 混淆矩阵
     * @param text   text
     * @return result
     */
    private static int[][] columnMixAlgorithm(int[][] matrix, int[][] text) {
        int[][] res = new int[2][2];
        res[0][0] = GFAdd(GFMultiply(matrix[0][0], text[0][0]), GFMultiply(matrix[0][1], text[1][0]));
        res[0][1] = GFAdd(GFMultiply(matrix[0][0], text[0][1]), GFMultiply(matrix[0][1], text[1][1]));
        res[1][0] = GFAdd(GFMultiply(matrix[1][0], text[0][0]), GFMultiply(matrix[1][1], text[1][0]));
        res[1][1] = GFAdd(GFMultiply(matrix[1][0], text[0][1]), GFMultiply(matrix[1][1], text[1][1]));
        return res;
    }

    /**
     * GF(2^4)加法
     *
     * @param a a
     * @param b b
     * @return res
     */
    public static int GFAdd(int a, int b) {
        return a ^ b;
    }

    /**
     * GF(2^4)乘法
     *
     * @param a a
     * @param b b
     * @return res
     * @author ChatGPT
     */
    private static int GFMultiply(int a, int b) {
        int product = 0;

        for (int i = 0; i < 4; i++) {
            if ((b & 1) == 1) {
                product ^= a;
            }

            boolean isHighBitSet = (a & 0b1000) != 0;

            a <<= 1;

            if (isHighBitSet) {
                a ^= 0b10011; // 不可约多项式 x^4 + x + 1
            }

            b >>= 1;
        }

        return product & 0b1111; // 保留结果的最低4位，以确保在GF(2^4)伽罗华域内
    }

    /**
     * 将二进制转化为2x2的半字节矩阵(其中第一列为第一个字节，第二列为第二个字节)
     *
     * @param text text
     * @return res
     */
    public static int[][] transformToArray(int text) {
        StringBuilder binary = new StringBuilder(Integer.toBinaryString(text));
        while (binary.length() < 16) {
            binary.insert(0, "0");
        }
        int[][] res = new int[2][2];
        res[0][0] = Integer.parseInt(binary.substring(0, 4), 2);
        res[1][0] = Integer.parseInt(binary.substring(4, 8), 2);
        res[0][1] = Integer.parseInt(binary.substring(8, 12), 2);
        res[1][1] = Integer.parseInt(binary.substring(12, 16), 2);
        return res;
    }

    /**
     * 将半字节的左下和右上进行交换
     *
     * @param text text
     */
    public static void help(int[][] text) {
        int temp = text[0][1];
        text[0][1] = text[1][0];
        text[1][0] = temp;
    }

    /**
     * 将2x2的半字节矩阵转化为16位的二进制数
     *
     * @param text text
     * @return result
     */
    public static String transformToBinary(int[][] text) {
        help(text);
        StringBuilder sb = new StringBuilder();
        StringBuilder binary;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                binary = new StringBuilder(Integer.toBinaryString(text[i][j]));
                while (binary.length() < 4) {
                    binary.insert(0, "0");
                }
                sb.append(binary);
            }
        }

        return sb.toString();
    }

}
