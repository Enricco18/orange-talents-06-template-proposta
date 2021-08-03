package br.com.zupacademy.enricco.proposta.utils;

import br.com.zupacademy.enricco.proposta.utils.crypto.Crypto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

public class Encryptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Test
    public void test(){
        Crypto crypto = new Crypto("e06f2b57e9238978", "e06f2b57e9238978");
        String toEncode = "070.236.780-00";
        String um = crypto.encode(toEncode);
        String dois = crypto.encode(toEncode);
        logger.info("Log1:"+um+"|"+dois);
        Assert.assertTrue(!um.equals(dois));

        String decoded = crypto.decode(um);

        logger.info(decoded);
        logger.info(String.valueOf(decoded.equals(toEncode)));
        Assert.assertTrue(toEncode.equals(decoded));

    }
}
