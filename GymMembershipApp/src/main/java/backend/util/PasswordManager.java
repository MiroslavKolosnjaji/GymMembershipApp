package backend.util;



/**
 * @author Miroslav Kološnjaji
 */
import de.svws_nrw.ext.jbcrypt.BCrypt;

public class PasswordManager {

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }

}
