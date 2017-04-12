package org.pravus.qpojo.util;

import java.util.ArrayList;
import java.util.List;

public class ListAndStringUtils {

    public static List<Character> stringToCharacterList(String string) {
        List<Character> stringAsList = new ArrayList<>();
        for (char c : string.toCharArray()) {
            stringAsList.add(c);
        }
        return stringAsList;
    }

    public static String characterListToString(List<Character> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : list) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

}
