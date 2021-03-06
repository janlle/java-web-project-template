package com.domain.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * <p>
 *
 * @author leone
 * @since 2018-09-19
 **/
@Slf4j
public class BcryptUtil {

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static Cipher cipher;

    private static KeyFactory keyFactory;

    private static KeyPairGenerator keyPairGenerator;

    static {
        try {
            cipher = Cipher.getInstance("RSA");
            keyFactory = KeyFactory.getInstance("RSA");
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
//        byte[] hellos = encrypt("hello");
//        System.out.println(hellos);
//        String s = encoder.encodeToString(hellos);
//        String result = decrypt(s);
//        System.out.println(s);
//        // 5d41402abc4b2a76b9719d911017c592
//        String a = DigestUtils.md5DigestAsHex("hello".getBytes());
//        String md5 = MD5("hello");
//        System.out.println(md5);
//        System.out.println(a);
        System.out.println(StandardCharsets.UTF_8);
    }


    /**
     * MD5加密
     *
     * @param content
     * @return
     */
    public static String MD5(String content) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] hashCode = messageDigest.digest();
            return new HexBinaryAdapter().marshal(hashCode).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        URL pri_url = BcryptUtil.class.getClassLoader().getResource("security/pri_key.pen");
        File pri_file = new File(pri_url.getFile());
        RSAPrivateKey rsaPrivateKey = loadPrivateKeyByFile(pri_file);
        return pri_key_decode(decoder.decode(content), rsaPrivateKey);
    }

    /**
     * @param content
     * @return
     */
    public static byte[] encrypt(String content) {
        URL pub_url = BcryptUtil.class.getClassLoader().getResource("security/pub_key.pen");
        File pub_file = new File(pub_url.getFile());
        RSAPublicKey rsaPublicKey = loadPublicKeyByFile(pub_file);
        return pub_key_encode(content, rsaPublicKey);
    }


    /**
     * @param file
     * @return
     */
    public static RSAPublicKey loadPublicKeyByFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String key = sb.toString();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoder.decode(key));
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param file
     * @return
     */
    public static RSAPrivateKey loadPrivateKeyByFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String key = sb.toString();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoder.decode(key));
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param content
     * @param pri_key
     * @return
     */
    public static byte[] pri_key_encode(String content, RSAPrivateKey pri_key) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, pri_key);
            return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param content
     * @param pub_key
     * @return
     */
    public static byte[] pub_key_encode(String content, RSAPublicKey pub_key) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, pub_key);
            return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param content
     * @param pri_key
     * @return
     */
    public static String pri_key_decode(byte[] content, RSAPrivateKey pri_key) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, pri_key);
            return new String(cipher.doFinal(content), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param content
     * @param pub_key
     * @return
     */
    public static String pub_key_decode(byte[] content, RSAPublicKey pub_key) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, pub_key);
            return new String(cipher.doFinal(content), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
