package com.example.sistemaBanco.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	// Declaração de uma variável estática md do tipo MessageDigest a variável é
	// inicializada como null.
	private static MessageDigest md = null;

	static { // Bloco de inicialização estática
		try {
			// tentando obter uma instância do MessageDigest usando o algoritmo MD5 e
			// armazenando na variável md.
			md = MessageDigest.getInstance("MD5");
			// Se o algoritmo não for suportado, uma exceção NoSuchAlgorithmException é
			// tratada imprimindo o rastreamento da pilha.
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	// Método para gerar a chave
	// metodo que converte um array de bytes em uma representação hexadecimal
	private static char[] hexCodes(byte[] text) {
		// criando um array de caracteres (char[]) chamado hexOutput
		// para cada byte é representado 2 caracteres no hexadeciaml, por isso
		// multiliplica por 2
		char[] hexOutput = new char[text.length * 2]; // tamanho do array é o dobro do tamanho do array de bytes text
		String hexString; // usada para armazenar temporariamente cada par de caracteres hexadecimais
							// enquanto iteramos pelo array de bytes text e os convertemos para a
							// representação hexadecimal.

		// itera sobre o array de bytes, converte cada byte em para hexadecimal com
		// Integer.toHexString, converte para maiúsculas com
		// toUpperCase e armazena os caracteres hexadecimais no array hexOutput, que é
		// retornado no final.
		for (int i = 0; i < text.length; i++) {
			hexString = "00" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}

	// método público que recebe o texto a ser criptografado
	public static String cryptography(String password) {
		// verifica se a variável md não é nula (se o algoritmo MD5 foi inicializado
		// corretamente),
		if (md != null) {
			// calcula o hash da senha fornecida usando md.digest(pwd.getBytes()) e converte
			// o resultado em uma representação hexadecimal usando o método hexCodes.
			return new String(hexCodes(md.digest(password.getBytes())));
		}
		return null;
	}
}
