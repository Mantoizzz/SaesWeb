package com.aes.saesweb.expand;

import com.aes.saesweb.core.Decrypt;
import com.aes.saesweb.core.Encrypt;
import lombok.extern.slf4j.Slf4j;

/**
 * 多重加密
 *
 * @author Mantozi
 */
@Slf4j
public class MultipleEncrypt {

    /**
     * 二重加密
     *
     * @param plainText  明文
     * @param secretKeyA 密钥前16位组成的状态矩阵
     * @param secretKeyB 密钥后16位组成的状态矩阵
     * @return 结果
     * @throws Exception exception
     */
    public static int[][] doubleEncrypt(int[][] plainText, int[][] secretKeyA, int[][] secretKeyB) throws Exception {
        return Encrypt.encrypt(Encrypt.encrypt(plainText, secretKeyA), secretKeyB);
    }

    /**
     * 三重加密
     *
     * @param plainText  明文
     * @param secretKeyA key
     * @param secretKeyB key
     * @param secretKeyC key
     * @return result
     * @throws Exception exception
     */
    public static int[][] tripleEncrypt(int[][] plainText, int[][] secretKeyA, int[][] secretKeyB, int[][] secretKeyC) throws Exception {
        return Encrypt.encrypt(Encrypt.encrypt(Encrypt.encrypt(plainText, secretKeyA), secretKeyB), secretKeyC);
    }

    /**
     * @param text       text
     * @param secretKeyA A
     * @param secretKeyB B
     * @return result
     * @throws Exception exception
     */
    public static int[][] doubleDecrypt(int[][] text, int[][] secretKeyA, int[][] secretKeyB) throws Exception {
        return Decrypt.decrypt(Decrypt.decrypt(text, secretKeyB), secretKeyA);
    }

    /**
     * @param text       text
     * @param secretKeyA A
     * @param secretKeyB B
     * @param secretKeyC C
     * @return result
     * @throws Exception exception
     */
    public static int[][] tripleDecrypt(int[][] text, int[][] secretKeyA, int[][] secretKeyB, int[][] secretKeyC) throws Exception {
        return Decrypt.decrypt(Decrypt.decrypt(Decrypt.decrypt(text, secretKeyC), secretKeyB), secretKeyA);
    }

}
