package study.moment.global.jwt;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.moment.global.exception.ErrorCode;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Transactional
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire-time}")
    private long expiredTime;

    private Key generateKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String authentication) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + expiredTime * 1000);

        return Jwts.builder()
                .setSubject(authentication)
                .signWith(generateKey(secret), SignatureAlgorithm.HS512)
                .setExpiration(expireTime)
                .compact();
    }

    public void validateToken(String token) {
        try {
            parseToken(token);
        } catch (SecurityException | MalformedJwtException e) {
            throw new AuthException(ErrorCode.INVALID_TOKEN_SIGNATURE);
        } catch (ExpiredJwtException e) {
            throw new AuthException(ErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new AuthException(ErrorCode.INVALID_TOKEN);
        }
    }

    public String decodeToken(String token) {
        return parseToken(token).getBody().getSubject();
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey(secret))
                .build()
                .parseClaimsJws(token);
    }

    public static <T> T decodePayload(String token, Class<T> clazz) {
        String jwtPayload = token.split("\\.")[1];
        Base64.Decoder base64Decoder = Base64.getUrlDecoder();
        String payload = new String(base64Decoder.decode(jwtPayload));
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return objectMapper.readValue(payload, clazz);
        } catch (Exception e) {
            throw new IllegalStateException("토큰의 페이로드 추출 중 예외가 발생했습니다.", e);
        }
    }
}
