package fs;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String tagClass, String tagMethod,
                                    String name, String value) {
        super(tagClass + "." + tagMethod + "(): " + name + " = " + value);
    }

    public InvalidArgumentException(String tagClass, String tagMethod,
                                    String reason) {
        super(tagClass + "." + tagMethod + "(): " + reason);
    }
}
