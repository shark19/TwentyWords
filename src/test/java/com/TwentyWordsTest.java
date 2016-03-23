package com;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TwentyWordsTest {
    /*
        синий Синий !СИНИЙ,
        красный. кРасный: зеленый!65
        *Black black- WHITE.
    */
    private String fileName = "/home/chist/IdeaProjects/TwentyPopularWords/src/test/java/com/test.txt";

    //Корректно ли список отбирает слова
    @org.junit.Test
    public void testCountOfWordsInList() throws Exception {
        ArrayList<String> list = TwentyWords.createWordsList(fileName);
        assertEquals(9, list.size());
    }

    //Все ли слова переносятся в карту
    @org.junit.Test
    public void testCountOfWordsInMap() throws Exception {
        LinkedHashMap<String, Integer> map = TwentyWords.createMapSortedByValue(TwentyWords.createWordsList(fileName));
        assertEquals(5, map.size());
    }

    //Правильно ли подсчитывается количество вхождений слова в список
    @org.junit.Test
    public void testCountingInMap() throws Exception {
        LinkedHashMap<String, Integer> map = TwentyWords.createMapSortedByValue(TwentyWords.createWordsList(fileName));
        assertEquals(3, (int) map.get("СИНИЙ"));
    }

    //Верно ли сортируется карта
    @org.junit.Test
    public void testSortByValue() throws Exception {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        map.put("заяц", 10);
        map.put("волк", 25);
        map.put("медведь", 17);
        map.put("лиса", 30);

        map = TwentyWords.sortByValue(map);

        list.addAll(map.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()));

        assertEquals(30, (int) list.get(0));
        assertEquals(25, (int) list.get(1));
        assertEquals(17, (int) list.get(2));
    }
}