package com.aes.saesweb.expand;

import com.aes.saesweb.core.Decrypt;
import com.aes.saesweb.core.Encrypt;
import com.aes.saesweb.core.Process;

/**
 * @author Mantozi
 */
public class ASCII {

    /**
     * 将ASCII转化为2x2半字节矩阵
     *
     * @param arr arr
     * @return result
     */
    public static int[][] transformToText(char[] arr) {
        int binary = (arr[0] << 8) | arr[1];
        return Process.transformToArray(binary);
    }

    /**
     * 将2x2半字节矩阵转化为ASCII
     *
     * @param text text
     * @return result
     */
    public static char[] transformToASCII(int[][] text) {
        String binary = Process.transformToBinary(text);
        String str1 = binary.substring(0, 8);
        String str2 = binary.substring(8);
        return new char[]{(char) Integer.parseInt(str1, 2), (char) Integer.parseInt(str2, 2)};
    }

    /**
     * 针对ASCII的加密
     *
     * @param text      text
     * @param secretKey key
     * @return result
     * @throws Exception exception
     */
    public static char[] encrypt(char[] text, int[][] secretKey) throws Exception {
        int[][] plainText = transformToText(text);
        int[][] encryptedText = Encrypt.encrypt(plainText, secretKey);
        return transformToASCII(encryptedText);
    }

    /**
     * 针对ASCII的解密
     *
     * @param ascii ascii
     * @param secretKey secretKey
     * @return result
     * @throws Exception exception
     */
    public static char[] decrypt(char[] ascii, int[][] secretKey) throws Exception {
        int[][] matrix = transformToText(ascii);
        int[][] text = Decrypt.decrypt(matrix, secretKey);
        return transformToASCII(text);
    }

}
