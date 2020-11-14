import java.io.*;

/**
 * Launches the program.
 *
 * @author Alex
 */
public class Main {
    private static final String SAVE_DIR = "phase1/save/app.ser";
    private static File file = new File(SAVE_DIR);

    public static void main(String[] args) {

        // if file exists, load app from file, else create new app instance
        ConferenceApp app = file.exists() ? fromFile() : new ConferenceApp();
        app.launch();

        // once user safely closes app, app saved to file
        toFile(app);
    }

    /**
     * Class for reading from file.
     * @return deserialized ConferenceApp instance.
     */
    private static ConferenceApp fromFile() {
        ConferenceApp app = null;

        // deserialization
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fis)) {

            app = (ConferenceApp) in.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return app;
    }

    /**
     * Serializes ConferenceApp.
     * @param app ConferenceApp instance.
     */
    private static void toFile(ConferenceApp app) {
        try {
            if (!file.exists()) file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // serializing
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {

            out.writeObject(app);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
