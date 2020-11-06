package Models;

/**
 * Abstract Model class. Extending classes may store content as any object, as long as it is
 * returned as a string in getContent()
 *
 * @author Alex
 */
public abstract class AbstractModel {

    /**
     * Gets the content of the model as a string. String may be separated with newline characters.
     * @return content of model as a string.
     */
    public abstract String getContent();
}
