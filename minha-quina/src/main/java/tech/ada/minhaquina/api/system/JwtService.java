package tech.ada.minhaquina.api.system;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.ada.minhaquina.api.blockedToken.BlockedTokenModel;
import tech.ada.minhaquina.api.blockedToken.BlockedTokenRepository;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final BlockedTokenRepository blockedTokenRepository;

    @Value("${security.jwt.token.secret-key}")
    private String SECRET;

    public JwtService(BlockedTokenRepository blockedTokenRepository) {
        this.blockedTokenRepository = blockedTokenRepository;
    }

    private Key getSignKInkey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(SECRET.getBytes()));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = this.extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKInkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String createToken(UserDetails user) {
        return this.createToken(new HashMap<>(), user);
    }

    public String createToken(
            Map<String, Object> claims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1hr
                .signWith(getSignKInkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails user) {
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token) && tokenNotBlocked(token);
    }

    private boolean tokenNotBlocked(String token) {
        return !blockedTokenRepository.existsByBlockedToken(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public void saveTokenToBlockedList(String token) {
        var tokenModel = new BlockedTokenModel();
        tokenModel.setBlockedToken(token);
        blockedTokenRepository.save(tokenModel);
    }
}
