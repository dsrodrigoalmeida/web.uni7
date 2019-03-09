package br.edu.uni7.questoes1e3.business;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

public class JWTUtil {
	public static final String BEARER = "Bearer ";
	private static RsaJsonWebKey rsaJsonWebKey;
	static {
		try {
			rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
		} catch (JoseException e) {
			throw new IllegalStateException(e);
		}
	}

	public static String validar(String jwt) throws InvalidJwtException {
		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireJwtId().setRequireSubject().setRequireNotBefore()
				.setRequireExpirationTime().setVerificationKey(rsaJsonWebKey.getKey()).build();
// Valida e extrai as informacoes
		JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
		return (String) jwtClaims.getClaimValue("sub");
	}

	public static String criar(String funcionario) throws JoseException {
		JwtClaims claims = new JwtClaims();
		claims.setSubject(funcionario);
		claims.setIssuedAtToNow();
		claims.setGeneratedJwtId();
		claims.setNotBeforeMinutesInThePast(1);
		claims.setExpirationTimeMinutesInTheFuture(10);
		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toJson());
		jws.setKey(rsaJsonWebKey.getPrivateKey());
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

		return jws.getCompactSerialization();
	}
}
