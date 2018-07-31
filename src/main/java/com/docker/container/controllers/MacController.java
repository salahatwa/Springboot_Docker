package com.docker.container.controllers;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docker.container.entities.MacDTO;

/**
 * @author atwa Jul 23, 2018
 */

@RestController
@RequestMapping("/api/mac")
public class MacController {

	private String API_SECRET = "salah";

	@GetMapping(value = "/{text}/{path}")
	public MacDTO getMac(@PathVariable String text, @PathVariable String path) {
		String backend = calculateSignature(text, path);

		System.out.println(">>>BackEndSignature:" + backend);

		MacDTO dto = new MacDTO();
		dto.setResult(backend);
		return dto;
	}

	private String calculateSignature(String data, String path) {
		System.out.println("calculateSignature " + " " + data + " " + path);
		String signature = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((data).getBytes());
			Mac mac = Mac.getInstance("HmacSHA512");
			mac.init(new SecretKeySpec(Base64.decodeBase64(API_SECRET.getBytes()), "HmacSHA512"));
			mac.update(path.getBytes());
			byte[] digest = md.digest();
			System.out.println("digest = " + Base64.encodeBase64String(digest));
			signature = Base64.encodeBase64String(mac.doFinal(digest));

			System.out.println("signature = " + signature);
		} catch (Exception e) {
		}
		return signature;
	}

}
