package br.com.zupacademy.enricco.proposta.utils.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

@Component
public class Crypto implements Encryptor{
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Value("${crypto.salt}")
    private String crypto;

    @Value("${crypto.key}")
    private String key;

    @Deprecated
    public Crypto() {
    }

    @Deprecated
    public Crypto(String crypto, String key) {
        this.crypto = crypto;
        this.key = key;
    }

    public String encode(String toEncode){
        return Encryptors.text(key,crypto).encrypt(toEncode);
    }

    public String decode(String decode){
        return   Encryptors.text(key,crypto).decrypt(decode);
    }
}
