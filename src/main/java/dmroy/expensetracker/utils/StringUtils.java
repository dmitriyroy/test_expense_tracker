package dmroy.expensetracker.utils;

import org.springframework.stereotype.Service;

/**
 *
 * Service for working with strings
 * for example, if the length of the string when storing in the database> what is declared in the database,
 * then we catch exeption. Therefore, you need to prepare the line before putting it into the database.
 */
@Service
public class StringUtils {

    public String substrVarchar(String inString, int lengthInDb) {
        if(inString == null){
            return null;
        }
        if(inString.isEmpty()){
            return inString.trim();
        }

        inString = inString.trim();
        inString = inString.length() > lengthInDb ? inString.substring(0,lengthInDb) : inString;
        return inString;
    }

}
