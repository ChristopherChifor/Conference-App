package Gateways;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Database class that stores T type objects as individual JSON files.
 * JSON Files are stored in: group_0058/phase2/database/objectName if the directory does not exist,
 * it is created.
 * <p>
 * Uses GSON library for serializing into JSON
 *
 * @param <T> objects being stored.
 * @author Alex
 */
public class JsonDatabase<T> implements IGateway<T> {
    private final String DIRECTORY_ROOT = "phase2/database/";
    private final File directory;
    private Gson gson = new Gson();
    private final String objectName;
    private final Class<T> type;

    /**
     * Constructor.
     *
     * @param objectName the name representative of the object class being stored.
     * @param type       type of object (e.g. if T = Event, then type = Event.class)
     */
    public JsonDatabase(String objectName, Class<T> type) {
        this.directory = new File(String.format("%s%s/", DIRECTORY_ROOT, objectName));
        this.objectName = objectName;
        this.type = type;

        // creates the directory if it does not exist
        if (!this.directory.exists()) {
            this.directory.mkdirs();
        }
    }

    /**
     * Serialized and writes obj to file. If id already exists, replaces existing entry with new entry.
     *
     * @param obj object.
     * @param id  unique identifier.
     */
    @Override
    public void write(T obj, String id) {
        File file = getFile(id);

        // deletes file if exists
        if (file.exists()) {
            try {
                file.delete();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        String asJson = gson.toJson(obj);

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(asJson);
            Thread.sleep(30); // gives time for OS to do what it needs to do
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * Retrieves object with id.
     *
     * @param id unique identifier.
     * @return object with id; null if object DNE.
     */
    @Override
    public T read(String id) {
        File file = getFile(id);

        if (!file.exists()) return null;

        // read file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str;
            StringBuilder builder = new StringBuilder();

            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }

            // deserialize from json
            return gson.fromJson(builder.toString(), type);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns and deletes entry with id.
     *
     * @param id unique identifier
     * @return object with id; null if object does not exist.
     */
    @Override
    public T delete(String id) {
        File file = getFile(id);

        if (!file.exists()) return null;
        T obj = read(id);

        try {
            file.delete();
            Thread.sleep(30); // gives time for OS to do what it needs to do
        } catch (SecurityException | InterruptedException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Returns a list of ids of objects stored in this database
     *
     * @return list of string ids. Null if could not access directory.
     */
    @Override
    public List<String> getIds() {
        Stream<String> stream = getIdStream();
        if (stream == null) return null;
        return stream.collect(Collectors.toList());

    }

    /**
     * Gets a stream of T elements from json that match the predicate.
     * @param predicate a predicate checked against each element to see if it should be included
     * @return a stream of elements that match the predicate.
     */
    @Override
    public Stream<T> filterStream(Predicate<T> predicate) {
        Stream<String> stream = getIdStream();
        if (stream == null) return null;
        return stream.map(e -> read(e))
                .filter(predicate);
    }

    /**
     * Gets a list of T elements from json that match the predicate.
     * @param predicate a predicate checked against each element to see if it should be included
     * @return a list of elements that match the predicate
     */
    @Override
    public List<T> filterList(Predicate<T> predicate){
        Stream<T> stream = filterStream(predicate);
        if (stream == null) return null;
        return stream.collect(Collectors.toList());
    }

    /**
     * Private helper method for getting a stream of id's of stored elements.
     *
     * @return stream of strings that are id's; null if Security Exception
     */
    private Stream<String> getIdStream() {
        try {
            return Arrays.stream(directory.list())
                    .filter(s -> s.contains(objectName))
                    .filter(s -> s.contains(".json"))
                    .map(s -> s.substring(objectName.length() + 1, s.length() - ".json".length()));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Concatenates directory path and object name and id and .json ending.
     *
     * @param id unique identifier
     * @return a file directory.
     */
    private File getFile(String id) {
        return new File(directory, String.format("%s-%s.json", objectName, id));
    }

}
