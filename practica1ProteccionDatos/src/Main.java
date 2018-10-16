
import practica1ProteccionDatos.SymmetricCipher;

public class Main {

    public static void main (String [ ] args) throws Exception {

    	String mensaje = new String();
    	byte [] bytess = new byte[48];
    	String key = new String();
    	
    	for (int i = 0; i < 40; i++) {
    		
    		mensaje = mensaje + 'a';
    	}
    	for (int i = 0; i < 16; i++) {
    		
    		key = key + 'b';
    	}
    	
    	bytess = SymmetricCipher.encryptCBC(mensaje.getBytes(), key.getBytes());

    	System.out.println(bytess.length);
    	
    	String s = new String (bytess);
    	System.out.println(s.length());
    	
    	//String s = new String (SymmetricCipher.encryptCBC(mensaje.getBytes(), key.getBytes()));
    	
    	//String d = new String(SymmetricCipher.decryptCBC(s.getBytes(), key.getBytes()));
    	
/*        System.out.println(s.length());
        System.out.println(s);*/

    } 

} 