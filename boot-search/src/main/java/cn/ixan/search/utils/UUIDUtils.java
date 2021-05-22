package cn.ixan.search.utils;

import cn.ixan.search.utils.base.TimeBasedUUIDGenerator;
import cn.ixan.search.utils.base.UUIDGenerator;

import java.util.UUID;

public final class UUIDUtils {
	public static void main(String[] args) {
		System.out.println(guid());
	}

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

	public static String guid() {
		UUIDGenerator uuidGenerator = new TimeBasedUUIDGenerator();
		return uuidGenerator.getBase64UUID();

	}

    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static boolean checkUuid(String uuid) {
        String regex = "(\\w{32,36})";
        return uuid.matches(regex);
    }

}
