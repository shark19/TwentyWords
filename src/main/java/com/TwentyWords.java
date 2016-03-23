package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TwentyWords {
    
    public static void main(String... args) {
        try {
            ArrayList<String> list = createWordsList(args[0]);

            LinkedHashMap<String, Integer> hm = createMapSortedByValue(list);

            printTopTwentyWords(hm);
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File not found");
        }
    }

    //Создает список слов, содержащихся в передаваемом файле
    public static ArrayList<String> createWordsList(String fileName){
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(fileName))){
            while (in.ready())
                sb.append(in.readLine());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Oops, Something wrong with input/output system");
        }

        //Заменяем пробелами все, кроме латиницы и кириллицы
        sb = new StringBuilder(sb.toString().replaceAll("[^A-Z|a-z|А-Я|а-я]", " "));

        StringTokenizer st = new StringTokenizer(sb.toString());

        while (st.hasMoreTokens()) {
            list.add(st.nextToken().toUpperCase()); //Попутно приводим к общему регистру
        }

        return list;
    }

    //Создает карту, отсортированную по значению. (слово, количество вхождений в файл)
    public static LinkedHashMap<String, Integer> createMapSortedByValue(ArrayList<String> list){
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        for (String el : list) {
            Integer am;
            am = map.get(el);

            //Добавляем элемент, если он уже есть увеличиваем значение на 1
            map.put(el, am == null ? 1 : am + 1);
        }

        map = sortByValue(map);

        return map;
    }

    //Сортирует карту по значению
    public static LinkedHashMap<String, Integer> sortByValue( LinkedHashMap<String, Integer> map ) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>( map.entrySet() );
        Collections.sort( list, (o1, o2) -> (o2.getValue()).compareTo( o1.getValue() ));

        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : list) {
            result.put( entry.getKey(), entry.getValue() );
        }

        return result;
    }


    //Выводит на экран 20 первых записей, содержащихся в карте
    public static void printTopTwentyWords(LinkedHashMap<String, Integer> map){
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for(Map.Entry pair : map.entrySet()){
            String k = (String) pair.getKey();
            Integer v = (Integer) pair.getValue();
            sb = new StringBuilder(sb.append(count+1).append(". ").append(k).append(" - ").append(v));
            count++;
            if(count == 20)break;
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
