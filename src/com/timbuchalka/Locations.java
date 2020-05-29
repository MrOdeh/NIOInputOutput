package com.timbuchalka;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {
        Path datObject = FileSystems.getDefault().getPath("locations.dat");
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(datObject)))) {
            for (Location location : locations.values()) {
                locFile.writeObject(location);
            }
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
//        Path locaFile = FileSystems.getDefault().getPath("locations_big.txt");
//        Path dirFile = FileSystems.getDefault().getPath("directions_big.txt");
//        try (BufferedWriter bufferLocal = Files.newBufferedWriter(locaFile);
//             BufferedWriter bufferDir = Files.newBufferedWriter(dirFile)) {
//            for (Location location : locations.values()) {
//                bufferLocal.write(location.getLocationID() + "," + location.getDescription() + "\n");
//                for (String direction : location.getExits().keySet()) {
//                    if (!direction.equalsIgnoreCase("Q")) {
//                        bufferDir.write(location.getLocationID() + "," + direction + ","
//                                + location.getExits().get(direction) + "\n");
//                    }
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println("IOException + " + e.getMessage());
//        }
    }

    static {
        Path localPath = FileSystems.getDefault().getPath("locations.dat");
        try (ObjectInputStream localFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(localPath)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Location location = (Location) localFile.readObject();
                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (InvalidClassException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOEXception " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }

//        Path locaFile = FileSystems.getDefault().getPath("locations_big.txt");
//        Path dirFile = FileSystems.getDefault().getPath("directions_big.txt");
//        try (Scanner scanner = new Scanner(Files.newBufferedReader(locaFile))) {
//            while (scanner.hasNextLine()) {
//                String[] inputs = scanner.nextLine().split(",");
//                System.out.println("Impacted : " + inputs[0] + ":" + inputs[1]);
//                locations.put(Integer.parseInt(inputs[0]), new Location(Integer.parseInt(inputs[0]), inputs[1], null));
//            }
//        } catch (IOException e) {
//            System.out.println("IOException : " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(dirFile))) {
//            String input;
//            while ((input = reader.readLine()) != null) {
//                String[] data = input.split(",");
//                System.out.println(data[0] + ":" + data[1] + ":" + data[2]);
//                Location location = locations.get(Integer.parseInt(data[0]));
//                location.addExit(data[1], Integer.parseInt(data[2]));
//            }
//        } catch (IOException e) {
//            System.out.println("IOEXception " + e.getMessage());
//        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
