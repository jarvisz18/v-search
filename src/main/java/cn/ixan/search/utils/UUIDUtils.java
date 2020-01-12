package cn.ixan.search.utils;

import java.util.UUID;

public final class UUIDUtils {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generate(){
        return UUID.randomUUID().toString();
    }

    public static boolean checkUuid(String uuid){
        //String regex = "(\\w{8}(-\\w{4}){3}-\\w{12}?)";
        String regex = "(\\w{32,36})";
        return uuid.matches(regex);
    }

}
