/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyLogic;

import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
/**
 *
 * @author cris
 */
public class Base64_ {
    
        public String decode(String s) {
             return StringUtils.newStringUtf8(Base64.decodeBase64(s));
        }
        public String encode(String s) {
             return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
        }  
        public String prueba(){
            String i = "//uQZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
            return i;
        }
}