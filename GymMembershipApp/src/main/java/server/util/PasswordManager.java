package server.util;

import de.svws_nrw.ext.jbcrypt.BCrypt;

/**
 * @author Miroslav Kološnjaji
 */
public class PasswordManager {

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }

}
