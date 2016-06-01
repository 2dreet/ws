/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacao;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

/**
 *
 * @author Jose
 */
public class Autenticacao {

    public static void main(String[] args) throws InvalidJwtException, UnsupportedEncodingException, JoseException, JSONException, MalformedClaimException {

        JSONObject obj = new JSONObject();
        obj.put("usuario", "jclan");

        JwtClaims claims = new JwtClaims();
        claims.setSubject(obj.toString());
        claims.setExpirationTimeMinutesInTheFuture(10);

        String secret = "jclanJcuser";
        Key key = new HmacKey(secret.getBytes("UTF-8"));

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(key);
        jws.setDoKeyValidation(false); // relaxes the key length requirement

        String jwt = jws.getCompactSerialization();

        System.out.println(jwt);

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireSubject()
                .setVerificationKey(key)
                .setRelaxVerificationKeyValidation() // relaxes key length requirement 
                .build();

        try {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            System.out.println("JWT validation succeeded! " + jwtClaims.getSubject().toString());
        } catch (InvalidJwtException e) {
            // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            System.out.println("Invalid JWT! " + e);
        }
    }

}
