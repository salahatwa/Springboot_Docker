package com.docker.container.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cit.vericash.common.Message;
import com.cit.vericash.common.Request;
import com.docker.container.utils.FileUtils;
import com.docker.container.utils.GenericResponse;
import com.docker.container.utils.RSAUtils;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/rsa")
public class EncryptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionController.class);

	public boolean isKeyFileSave = true;
	
	@GetMapping(value = "/generate_public_key")
	public GenericResponse getEncryptionPublicKey(HttpServletRequest request) {

		KeyPair keyPair = RSAUtils.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		saveKeysFiles(privateKey, publicKey);

		GenericResponse response = new GenericResponse();

		LOGGER.info("Public Key : {} ", Base64.encodeBase64String(publicKey.getEncoded()));

		LOGGER.info("Private Key : {} ", Base64.encodeBase64String(privateKey.getEncoded()));

		response.setStatusCode(HttpStatus.OK.value());
		response.getMapData().put("publicKey", Base64.encodeBase64String(publicKey.getEncoded()));
		response.getMapData().put("test", "success");
		Gson gs = new Gson();
		String jj = gs.toJson(response);
		System.err.println(">>>>>>>>>>>>>>" + jj);
		return response;
	}

	@PostMapping(value = "/decrypt_data")
	public Message decrypt(@RequestBody Request request) throws IOException {

		StringBuffer buffer = new StringBuffer();
		PrivateKey privateKey = RSAUtils.readPrivateKeyFromFile("E:\\Cryptography\\Private.key");

		LOGGER.info("Encrypted Message : {} ", request.toString());

		for (String element : request.getEncryptedMessage()) {
			buffer.append(RSAUtils.decrypt(element, privateKey));
		}

		LOGGER.info("Decrypt Message : {}", buffer.toString());

		Gson gs = new Gson();
		Message msg = gs.fromJson(buffer.toString(), Message.class);

		return msg;
	}

	public void saveKeysFiles(PrivateKey privateKey, PublicKey publicKey) {

		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec rsaPublicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			RSAPrivateKeySpec rsaPrivateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

			FileUtils.saveKey("E:\\Cryptography\\Public.key", rsaPublicKeySpec.getModulus(),
					rsaPublicKeySpec.getPublicExponent());
			FileUtils.saveKey("E:\\Cryptography\\Private.key", rsaPrivateKeySpec.getModulus(),
					rsaPrivateKeySpec.getPrivateExponent());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
