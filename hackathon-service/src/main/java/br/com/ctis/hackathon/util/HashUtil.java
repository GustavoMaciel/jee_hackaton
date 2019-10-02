package br.com.ctis.hackathon.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitária para geração de Hash
 * 
 * @author alysson.cordeiro@ctis.com.br
 *
 */
public class HashUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HashUtil.class);
	
	private HashUtil() {
		super();
	}
	
	/**
	 * Método para gerar Hash para determinada String
	 * 
	 * @param info - Informação que será gerado o hash
	 * @param algoritmo - Algoritmo, Ex: SHA-256
	 * 
	 * @return String - Hash gerado
	 */
	public static String gerarHash(String info, String algoritmo) {
		
		String hash = null;
		
		try {	
	        MessageDigest algorithm = MessageDigest.getInstance(algoritmo);	        
	        byte[] messageDigest = algorithm.digest(info.getBytes(StandardCharsets.UTF_8));
	        hash = DatatypeConverter.printHexBinary(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Erro ao gerar Hash " + algoritmo, e);
		} 

		return hash;
		
	}
}
