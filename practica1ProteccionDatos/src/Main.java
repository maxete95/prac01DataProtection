
import practica1ProteccionDatos.SymmetricCipher;
import practica1ProteccionDatos.SymmetricEncryption;

public class Main {

    public static void main (String [ ] args) throws Exception {

    	String mensaje = new String();
    	byte [] encryptedtext = new byte[48];
    	String key = new String();
    	
    	mensaje = "En un lugar de la mancha de cuyo nombre no quiero acordarme";
    	

    	for (int i = 0; i < 16; i++) {
    		
    		key = key + 'b';
    	}
    	

    	encryptedtext = SymmetricCipher.encryptCBC(mensaje.getBytes(), key.getBytes());
    	    	
    	
    	String d = new String(SymmetricCipher.decryptCBC(encryptedtext, key.getBytes()));
    	
    	System.out.println(d);
    } 

} 