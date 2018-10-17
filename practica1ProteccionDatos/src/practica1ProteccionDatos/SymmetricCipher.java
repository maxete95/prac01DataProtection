package practica1ProteccionDatos;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.*;
import java.security.InvalidKeyException;

public class SymmetricCipher {

	byte[] byteKey;
	static SymmetricEncryption s;
	static SymmetricEncryption d;
	
	static // Initialization Vector (fixed)
	
	byte[] iv = new byte[] { (byte)49, (byte)50, (byte)51, (byte)52, (byte)53, (byte)54, 
		(byte)55, (byte)56, (byte)57, (byte)48, (byte)49, (byte)50, (byte)51, (byte)52,
		(byte)53, (byte)54};

    /*************************************************************************************/
	/* Constructor method */
    /*************************************************************************************/
	public void SymmetricCipher() {
		
		
	}
	
    /*************************************************************************************/
	/* Method to encrypt using AES/CBC/PKCS5 */
    /*************************************************************************************/
	public static byte[] encryptCBC (byte[] input, byte[] byteKey) throws Exception {
		
		
		s = new SymmetricEncryption(byteKey);
		int modulus = 16 - input.length % s.AES_BLOCK_SIZE;
		
		byte [] temp = new byte [input.length + modulus + s.AES_BLOCK_SIZE];
		byte [] tempFinal = new byte [s.AES_BLOCK_SIZE];

		byte[] ciphertext = new byte[input.length + modulus + s.AES_BLOCK_SIZE];
		byte[] ciphertextFinal = new byte[input.length + modulus + s.AES_BLOCK_SIZE];

		int i = 0;
	
		System.arraycopy(input, 0, temp, 0, input.length);
				
			// Generate the plaintext with padding
		
		if (modulus == 0) {
			System.out.println("bloques enteros");
		} else {
			
			for ( int j = 0; j < modulus ; j++) {
				temp[input.length + j] = (byte) modulus;
			}

		}
		for ( int j = 0; j < s.AES_BLOCK_SIZE ; j++) {
			temp[input.length + modulus + j] = (byte) modulus;
		}
			
			// Generate the ciphertext
			
		for (i = 0; i < temp.length; i += 16) {
				
				if (i == 0) {
					for (int j = 0; j < 16 ; j++) {

						ciphertext[i + j] = (byte) (iv[j] ^ temp[i + j]);
						//cifrado

						tempFinal = SymmetricEncryption.encryptBlock(Arrays.copyOfRange(ciphertext, i, i+16));
						System.arraycopy(tempFinal, 0, ciphertextFinal, i, tempFinal.length);
					}
				}
				else {
					for (int j = 0; j < 16 ; j++) {
						ciphertext[i + j] = (byte) (ciphertext[i - 16 + j] ^ temp[i + j]);
						tempFinal = SymmetricEncryption.encryptBlock(Arrays.copyOfRange(ciphertext, i, i+16));
						System.arraycopy(tempFinal, 0, ciphertextFinal, i, tempFinal.length);
					}
			}
		}
		
		
		return ciphertextFinal;
	}
	
	/*************************************************************************************/
	/* Method to decrypt using AES/CBC/PKCS5 */
    /*************************************************************************************/
	
	
	public static byte[] decryptCBC (byte[] input, byte[] byteKey) throws Exception {
	
		
		byte[] decryptedtext = new byte [input.length];	
		byte [] temp = new byte [input.length];

		int i = 0;
		
		d = new SymmetricEncryption(byteKey);
		
		decryptedtext = d.decryptBlock(input);
			
		
		for (i = 0; i < decryptedtext.length; i += 16) {
			
			if (i == 0) {
				for (int j = 0; j < 16 ; j++) {

					temp[i + j] = (byte) (iv[j] ^ decryptedtext[i + j]);

				}
			}
			else {
				for (int j = 0; j < 16 ; j++) {
					temp[i + j] = (byte) (decryptedtext[i - 16 + j] ^ temp[i + j]);
				}

		}
	}
		//quitar el padding

		return temp;
	}
	
}

