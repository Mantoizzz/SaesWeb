package com.aes.saesweb.ui;

import com.aes.saesweb.core.Decrypt;
import com.aes.saesweb.core.Encrypt;
import com.aes.saesweb.core.Process;
import com.aes.saesweb.expand.ASCII;
import com.aes.saesweb.expand.MultipleEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SAESController {

    @PostMapping(value = "/encrypt")
    public ResponseEntity<Payload> processEncrypt(@RequestBody Request request) throws Exception {
        String text = request.getText();
        String key = request.getKey();
        Boolean bool = request.getIsEncrypt();
        log.info("text:{}", text);
        int binary = Integer.parseInt(text, 2);
        int[][] input = Process.transformToArray(binary);
        binary = Integer.parseInt(key, 2);
        int[][] secretKey = Process.transformToArray(binary);
        int[][] res;
        if (bool) {
            res = Decrypt.decrypt(input, secretKey);
        } else {
            res = Encrypt.encrypt(input, secretKey);
        }
        Payload payload = new Payload(Process.transformToBinary(res));
        return ResponseEntity.ok(payload);
    }

    @PostMapping(value = "/ascii")
    public ResponseEntity<Payload> processASCII(@RequestBody Request request) throws Exception {
        StringBuilder sb = new StringBuilder();
        String text = request.getText();
        Boolean bool = request.getIsEncrypt();
        char[] charArr = text.toCharArray();
        String key = request.getKey();
        int binary = Integer.parseInt(key, 2);
        int[][] secretKey = Process.transformToArray(binary);
        char[] res;
        if (bool) {
            res = ASCII.decrypt(charArr, secretKey);
        } else {
            res = ASCII.encrypt(charArr, secretKey);
        }
        sb.append(res[0]);
        sb.append(res[1]);
        Payload payload = new Payload(sb.toString());
        return ResponseEntity.ok(payload);
    }

    @PostMapping(value = "/multiple")
    public ResponseEntity<Payload> processMultiple(@RequestBody MultipleRequest multipleRequest) throws Exception {
        String text = multipleRequest.getText();
        String keyA = multipleRequest.getKeyA();
        String keyB = multipleRequest.getKeyB();
        String keyC = multipleRequest.getKeyC();
        Boolean bool = multipleRequest.getIsEncrypt();
        int binary = Integer.parseInt(text, 2);
        int[][] input = Process.transformToArray(binary);
        binary = Integer.parseInt(keyA, 2);
        int[][] secretKeyA = Process.transformToArray(binary);
        binary = Integer.parseInt(keyB, 2);
        int[][] secretKeyB = Process.transformToArray(binary);
        binary = Integer.parseInt(keyC, 2);
        int[][] res;
        int[][] secretKeyC = Process.transformToArray(binary);
        if (bool) {
            res = MultipleEncrypt.tripleDecrypt(input, secretKeyA, secretKeyB, secretKeyC);
        } else {
            res = MultipleEncrypt.tripleEncrypt(input, secretKeyA, secretKeyB, secretKeyC);
        }

        Payload payload = new Payload(Process.transformToBinary(res));
        return ResponseEntity.ok(payload);
    }

    @PostMapping(value = "/double")
    public ResponseEntity<Payload> processDouble(@RequestBody DoubleRequest doubleRequest) throws Exception {
        String text = doubleRequest.getText();
        String keyA = doubleRequest.getKeyA();
        String keyB = doubleRequest.getKeyB();
        Boolean bool = doubleRequest.getIsEncrypt();
        int binary = Integer.parseInt(text, 2);
        int[][] input = Process.transformToArray(binary);
        binary = Integer.parseInt(keyA, 2);
        int[][] secretKeyA = Process.transformToArray(binary);
        binary = Integer.parseInt(keyB, 2);
        int[][] secretKeyB = Process.transformToArray(binary);
        int[][] res;
        if (bool) {
            res = MultipleEncrypt.doubleDecrypt(input, secretKeyA, secretKeyB);
        } else {
            res = MultipleEncrypt.doubleEncrypt(input, secretKeyA, secretKeyB);
        }
        Payload payload = new Payload(Process.transformToBinary(res));
        return ResponseEntity.ok(payload);
    }

}
