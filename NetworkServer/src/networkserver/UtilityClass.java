/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkserver;

/**
 *
 * @author wajon
 */
public class UtilityClass 
{
    private boolean checkSpaces(String string)
    {
        boolean hasSpaces = false;
        int i = 0;
        String stringIndex;
        
        while (i < string.length())
        {
            stringIndex = Character.toString(string.charAt(i));
            if (" ".equals(stringIndex))
            {
                hasSpaces = true;
                break;
            }
            i++;
        }
        return hasSpaces;
    }
}
