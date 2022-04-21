package lv.adz;

import lv.adz.magic.Entity;
import lv.adz.magic.Group;
import lv.adz.magic.ManyEnt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static Set<ManyEnt> uniqStrings = new HashSet<>();
    private static Map<Entity, ArrayList<ManyEnt>> container = new HashMap<>();
    private static Map<Integer, ArrayList<Group>> orderg = new TreeMap<>(Collections.reverseOrder());
    private static int counter = 0;

    public static void main(String[] args) {
        Long start_time = System.currentTimeMillis();
        String Line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\lng.csv"));
            while ((Line = reader.readLine()) != null) {
                filterData(Line.replace("\"", "").split(";", -1));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        noMatch();
        secondGroup();
        print();
        System.out.println("Work time --> " + ((System.currentTimeMillis() - start_time) / 1000));
    }

    private static void filterData(String[] values) {
        if (values.length == 3) {
            ManyEnt manyEnt = new ManyEnt(values);
            if (!uniqStrings.contains(manyEnt) && manyEnt.checkStatus()) {
                uniqStrings.add(manyEnt);
                firstGroup(manyEnt);
            }
        }
    }

    private static void firstGroup(ManyEnt manyEnt) {
        for (Entity entity : manyEnt.getStatusEntities()) {
            ArrayList<ManyEnt> x = container.getOrDefault(entity, new ArrayList<>());
            x.add(manyEnt);
            container.put(entity, x);
        }
    }

    private static void noMatch() {
        Map<Entity, ArrayList<ManyEnt>> box = new HashMap<>();
        for (Map.Entry<Entity, ArrayList<ManyEnt>> in : container.entrySet()) {
            if (in.getValue().size() > 1) {
                box.put(in.getKey(), in.getValue());
            }
        }
        container = box;
    }

    private static void secondGroup() {
        tempGroup();
        for (Map.Entry<Entity, ArrayList<ManyEnt>> in : container.entrySet()) {
            ArrayList<Group> x = orderg.getOrDefault(in.getValue().size(), new ArrayList<>());
            Group group = new Group(in.getValue());
            x.add(group);
            orderg.put(group.size(), x);
            counter++;
        }
    }

    private static void tempGroup() {
        Map<ManyEnt, Integer> entCount = new HashMap<>();
        for (Map.Entry<Entity, ArrayList<ManyEnt>> in : container.entrySet()) {
            ArrayList<ManyEnt> ennt = in.getValue();
            for (ManyEnt manyEnt : ennt) {
                int value = entCount.getOrDefault(manyEnt, 0);
                entCount.put(manyEnt, value + 1);
            }
        }
        List<Set<ManyEnt>> meSubGrop = new ArrayList<>();
        for (Map.Entry<ManyEnt, Integer> in : entCount.entrySet()) {
            if (in.getValue() > 1) {
                Set<ManyEnt> set = new HashSet<>();
                for (Entity entity : in.getKey().getStatusEntities()) {
                    if (container.containsKey(entity)) {
                        set.addAll(container.remove(entity));
                    }
                }
                if (set.size() > 1) {
                    meSubGrop.add(set);
                }
            }
        }
        for (Set<ManyEnt> subGroup : meSubGrop) {
            ArrayList<Group> x = orderg.getOrDefault(subGroup.size(), new ArrayList<>());
            Group group = new Group(new ArrayList<>(subGroup));
            x.add(group);
            orderg.put(group.size(), x);
            counter++;
        }

    }
    public static void print() {
        System.out.println("Кол-во групп " + counter);
        int count = 1;
        for (Map.Entry<Integer, ArrayList<Group>> in : orderg.entrySet()) {
            for(Group group : in.getValue()) {
                System.out.println("Группа " + count + "\n");
                System.out.println(group);
                count++;
            }
        }
    }
}