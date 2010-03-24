/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package steganographyException;

/**
 *
 * @author vitaa
 */

import java.io.IOException;

public class BadQuantityArgumentsException extends IOException{
    public BadQuantityArgumentsException(){}
    public BadQuantityArgumentsException(String s)
    {System.out.println(s);}

}
