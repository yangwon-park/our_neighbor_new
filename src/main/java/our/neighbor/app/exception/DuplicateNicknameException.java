package our.neighbor.app.exception;

public class DuplicateNicknameException extends RuntimeException {

    public DuplicateNicknameException(String nickname) {
        super("이미 존재하는 닉네임입니다. nickname :: " + nickname);
    }
}
