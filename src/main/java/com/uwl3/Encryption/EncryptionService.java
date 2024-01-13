package com.uwl3.Encryption;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Service
public class EncryptionService {

    public String decodeValue(String value) throws UnsupportedEncodingException {
        byte[] bytes = value.getBytes("UTF-8");
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    public String encodeValue(String value){

        byte[] decoded = Base64.getDecoder().decode(value);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        return decodedStr;
    }




}
