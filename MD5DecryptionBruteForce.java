import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* @author Yogesh
 * 
 * MD5 Decryption using Brute-Force Method
 * 
 * Input: Concatenated MD5 Hash Value for secret String with 
 * string prefixes of length increasing by 2
 * (e.g. Original_hash = "MD5of(He) + MD5of(Hell) + (MD5of(Hello ))" )
 * 
 */

public class MD5DecryptionBruteForce {

    public static String toHexString(byte[] b)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++)
        {
            sb.append(String.format("%02X", b[i] & 0xFF));
        }
        return sb.toString();
    }
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        String original_hash = "Enter Hash Value Here";
		if(original_hash.length()%32 != 0){
        	System.out.println("Incorrect Hash Value");
        	return;
        }
        int length = original_hash.length()/32;
        String[] string_series = new String[length];
        int w=0;
        for(int i=0; i < original_hash.length(); i = i+32){
        	string_series[w++] = original_hash.substring((i), (i+32));
        }
        String characterString = "abcdefghijklmnopqrstuvwxyz_.@+!0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        String prev = "";
        outer : for(String original_hash_selector : string_series){
        	for(int i=0; i<characterString.length(); i++){
        		for(int j=0; j< characterString.length(); j++){
        			String s = prev;
        			s = s + characterString.charAt(i)+ characterString.charAt(j);
        			String newHash = toHexString(md.digest(s.getBytes()));
        			boolean flag = original_hash_selector.toLowerCase().equals(newHash.toLowerCase());
        			if(flag){
        				System.out.println(s);
        				prev = s;
        				continue outer;
        			}
        		}
        	}
        }
        System.out.println("Match Found: " + prev);
        return;
	}
}
