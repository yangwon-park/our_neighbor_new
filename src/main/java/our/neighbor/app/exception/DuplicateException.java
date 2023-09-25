package our.neighbor.app.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String type, String value) {
        super("이미 존재하는 " + type + "입니다. :: " + value);
    }
}
