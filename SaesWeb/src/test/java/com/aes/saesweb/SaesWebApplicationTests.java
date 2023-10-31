package com.aes.saesweb;

import com.aes.saesweb.core.Decrypt;
import com.aes.saesweb.core.Encrypt;
import com.aes.saesweb.core.Process;
import com.aes.saesweb.expand.ASCII;
import com.aes.saesweb.expand.CBC;
import com.aes.saesweb.expand.MeetInMiddle;
import com.aes.saesweb.expand.MultipleEncrypt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class SaesWebApplicationTests {

    @Test
    void testForEncrypt() throws Exception {

        int[][] arrSecret = {
                {0xA, 0x3},
                {0x7, 0xB}
        };
        int[][] arrPlain = {
                {0x6, 0x6},
                {0xF, 0xB}
        };
        int[][] res = Encrypt.encrypt(arrPlain, arrSecret);
        System.out.println("Arrays.deepToString(res) = " + Arrays.deepToString(res));

        int[][] decryptRes = Decrypt.decrypt(res, arrSecret);
        System.out.println("Arrays.deepToString(decryptRes) = " + Arrays.deepToString(decryptRes));

    }

    @Test
    void testForMultipleEncrypt() throws Exception {
        int[][] arrPlain = {
                {0x5, 0x8},
                {0xA, 0x2}
        };
        int[][] secretKeyA = {
                {0x4, 0xF},
                {0xC, 0x5}
        };
        int[][] secretKeyB = {
                {0x8, 0x2},
                {0xB, 0x1}
        };
        int[][] encryptedText = MultipleEncrypt.doubleEncrypt(arrPlain, secretKeyA, secretKeyB);
        System.out.println("Arrays.deepToString(encryptedText) = " + Arrays.deepToString(encryptedText));

        int[][] decryptedText = MultipleEncrypt.doubleDecrypt(encryptedText, secretKeyA, secretKeyB);
        System.out.println("Arrays.deepToString(decryptedText) = " + Arrays.deepToString(decryptedText));
    }

    @Test
    void testForMeetInMiddleAttack() throws Exception {
        int[][] arrPlain = {
                {0x5, 0x8},
                {0xA, 0x2}
        };

        int[][] encryptText = {
                {0x1, 0x7},
                {0x9, 0x6}
        };
        List<int[][]> result = MeetInMiddle.meetInMiddleAttack(arrPlain, encryptText);
        /*
        由于result集合中含有大量密钥，这里不作输出
         */

    }

    @Test
    void testForASCII() throws Exception {
        char[] arr = {'S', 'B'};
        int[][] arrSecret = {
                {0xA, 0x3},
                {0x7, 0xB}
        };
        char[] res = ASCII.encrypt(arr, arrSecret);
        System.out.println("Arrays.toString(res) = " + Arrays.toString(res));

        System.out.println("Arrays.toString(ASCII.decrypt(res,arrSecret)) = " + Arrays.toString(ASCII.decrypt(res, arrSecret)));
    }

    @Test
    void testForCBC() throws Exception {
        int[][] arrSecret = {
                {0xA, 0x3},
                {0x7, 0xB}
        };
        int[][] arrPlainA = {
                {0x5, 0x8},
                {0xA, 0x2}
        };
        int[][] arrPlainB = {
                {0x1, 0x7},
                {0x9, 0x6}
        };
        int[][] arrPlainC = {
                {0x8, 0x2},
                {0xB, 0x1}
        };
        List<int[][]> list = new ArrayList<>();
        list.add(arrPlainA);
        list.add(arrPlainB);
        list.add(arrPlainC);
        System.out.println("CBC.encrypt(list, arrSecret) = " + CBC.encrypt(list, arrSecret));

    }
}