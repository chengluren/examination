package org.dreamer.examination.service;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import java.io.*;
import java.net.URL;
import java.security.Key;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class EncryptTest {

//    @Test
//    public void testEncryptAndDecrypt(){
//        AesCipherService aec = new AesCipherService();
//        aec.setKeySize(128);
//
//        //Key key = aec.generateNewKey();
//        String p = "ybjtadmin121212";
//        byte[] key = getKey();
//        String encryptTxt = aec.encrypt(p.getBytes(),key).toHex();
//        System.out.println(encryptTxt);
//
//        //key = aec.generateNewKey();
//        String p2 = new String(aec.decrypt(Hex.decode(encryptTxt),key).getBytes());
//        System.out.println(p2);
//    }
//
//    @Test
//    public void testGenerateAeskey(){
//        AesCipherService aec = new AesCipherService();
//        aec.setKeySize(128);
//
//        Key key = aec.generateNewKey();
//        FileOutputStream fos = null;
//        try {
//            String filePath = this.getClass().getResource("/").getPath();
//            fos = new FileOutputStream(new File(filePath+"/key.txt"));
//            fos.write(key.getEncoded());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos!=null){
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Test
    public void testMd5Hash(){
         String p = "abc123";
         String salt = "7ghWPH";
         Md5Hash hash = new Md5Hash(p);
         String p2 = hash.toString()+salt;
         hash = new Md5Hash(p2);
         System.out.println(hash.toString());
    }

    private byte[] getKey() {
        URL url = this.getClass().getResource("/key.txt");
        if (url != null) {
            String path = url.getPath();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(new File(path));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                return buffer;
            } catch (IOException e) {
                e.printStackTrace();
                return "by-jd_123".getBytes();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return "by-jd_123".getBytes();
        }
    }
}
