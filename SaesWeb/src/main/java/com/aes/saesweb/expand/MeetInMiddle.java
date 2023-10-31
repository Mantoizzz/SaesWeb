package com.aes.saesweb.expand;

import com.aes.saesweb.core.Decrypt;
import com.aes.saesweb.core.Encrypt;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 中间相遇攻击
 *
 * @author Mantozi
 */
@Slf4j
public class MeetInMiddle {


    /**
     * 中间相遇攻击
     *
     * @return key
     * @throws Exception exception
     */
    public static List<int[][]> meetInMiddleAttack(int[][] plainText, int[][] cipherText) throws Exception {
        Map<ArrayWrapper, int[][]> map = new HashMap<>();
        List<int[][]> res = new ArrayList<>();
        for (int[][] key : generateKeys()) {
            int[][] encrypt = Encrypt.encrypt(plainText, key);
            map.put(new ArrayWrapper(encrypt), key);
        }
        for (int[][] key : generateKeys()) {
            int[][] decrypt = Decrypt.decrypt(cipherText, key);
            ArrayWrapper arr = new ArrayWrapper(decrypt);
            if (map.containsKey(arr)) {
                res.add(map.get(arr));
                res.add(key);
            }
        }
        return res;
    }

    /**
     * 产生所有密钥
     *
     * @return list
     */
    private static List<int[][]> generateKeys() {
        List<int[][]> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    for (int l = 0; l < 16; l++) {
                        list.add(new int[][]{
                                {i, j},
                                {k, l}
                        });
                    }
                }
            }
        }
        return list;
    }

    /**
     * int[][]包装类
     */
    private static class ArrayWrapper {
        private final int[][] array;

        public ArrayWrapper(int[][] array) {
            this.array = array;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayWrapper that = (ArrayWrapper) o;
            return Arrays.deepEquals(array, that.array);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(array);
        }
    }

}
