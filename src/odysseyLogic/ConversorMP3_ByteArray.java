/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author cris
 */
public class ConversorMP3_ByteArray {
    
    public void byteArrayToFile(byte[] byteArray, String outName) throws FileNotFoundException, IOException{
        FileUtils.writeByteArrayToFile(new File(outName), byteArray);
    }
}
