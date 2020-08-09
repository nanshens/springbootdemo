package com.ns.dynamicdatasource.utils;

import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;

/**
 * @author ns
 * @create 2020-08-09
 */

public class ValueEncryption {
    public static void main(String[] args) throws Exception {
        String password = "jdbc:postgresql://127.0.0.1:5432/dynamic_datasource_slave_2";

        String encodePassword = CryptoUtils.encrypt(password);
        System.out.println(encodePassword);

        //自定义publicKey
        String[] arr = CryptoUtils.genKeyPair(512);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + CryptoUtils.encrypt(arr[0], password));
    }
}
