package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] md5_ = messageDigest.digest(byteArrayOutputStream.toByteArray());
        String res = ""; //String.format("%x", new BigInteger(1, md5_));
        for (int i = 0; i < messageDigest.getDigestLength(); i++) {
            res = res + String.format("%02x", md5_[i]);
        }
 /*       if (res.length() < 32) {
            res = String.format("%32s", res);
            res.replace(" ", "0");
        }
 */       return res.equals(md5);
    }
}
