package com.example.UserManagementProject.Service;

import com.example.UserManagementProject.DTO.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Slf4j
public class JWTToken {

    private static final Base64.Decoder decoder = Base64.getUrlDecoder();
    // Your Secret Key for coding and decoding The Token
    private static final String secretKey = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
    // The Signature Algorithm
    private static final SignatureAlgorithm sa = SignatureAlgorithm.HS256;
    private static final SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());
    private static final DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

    public static String ExtractInfoFromToken(String token, String field) {
        log.info("Field: " + field);
        Map<String, String> Data = new HashMap<String, String>();
        String Chunk = new String(decoder.decode(token.split("\\.")[1]));
        Chunk = Chunk.substring(1, Chunk.length() - 1);
        Arrays.stream(Chunk.split(",")).forEach(ch -> {
            Data.put(ch.substring(1, ch.indexOf(":") - 1), ch.substring(ch.indexOf(":") + 2, ch.length() - 1));
        });
        return Data.get(field).toString();
    }

    public static String getToken(UserDTO userDTO) {
        String s = Jwts.builder()
                .claim("username", userDTO.getUserName())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now()
                        .plus(15, ChronoUnit.SECONDS)))
                .signWith(sa, secretKey)
                .compact();
        log.info(s);
        return s;
    }

    public static Boolean ValidateToken(String token) {
        String[] chunks = token.split("\\.");
        return !validator
                .isValid(chunks[0] + "." + chunks[1], chunks[2]);
    }
}
