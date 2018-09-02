package com.demo.controller;

import com.demo.util.AESUtil;
import com.demo.util.RSAUtil;
import com.demo.util.RedisUtil;
import com.demo.util.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("sign")
public class SignController {

    static final String AES_KEY = "THIRLYXIONG";

    public ResponseData getPublicKey(){
        Map<String,String> keyMap = RSAUtil.createKeys(1024);
        String  publicKey = keyMap.get("publicKey");
        String  privateKey = keyMap.get("privateKey");
        RedisUtil.set("privateKey",privateKey);
        return ResponseData.ok(publicKey);
    }

    public ResponseData decodePublicKey(String encryptedPublicKey) throws Exception{
        String publicKey = RSAUtil.privateDecrypt(encryptedPublicKey, RSAUtil.getPrivateKey((String)RedisUtil.get("privateKey")));
        String encodedData = RSAUtil.publicEncrypt(AES_KEY, RSAUtil.getPublicKey(publicKey));
        return ResponseData.ok(encodedData);
    }

    public ResponseData decodeSign(String sign){
        AESUtil.decrypt(sign, AES_KEY);
        return ResponseData.ok();
    }

}
