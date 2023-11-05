package com.example.demo.domain.utility.jwt;

import com.example.demo.domain.member.entity.UserRoleEnum;
import com.example.demo.domain.utility.exception.MemberEx;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    /**
     * 토큰 생성에 필요한 값들
     */
    
    // Header Key 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    // 사용자 권한 값의 Key
    public static final String AUTHORIZATION_KEY = "auth";
    
    // Token 식별자
        // Bearer : OAuth 2.0 인증 프로토콜에서 사용하는 인증 토큰의 유형을 명시하는 문자열
        // Bearer 다음에 오는 토큰 값은 하나의 문자열로 인식되어야 하기 때문에, Bearer 와 토큰 값 사이에는 한 칸 띄어쓰기가 필요
    private static final String BEARER_PREFIX = "Bearer ";

    // 토큰 만료 시간
    private static final long TOKEN_TIME = 60 * 60 * 1000L;

    // JWT 의 비밀 키 저장
    @Value("${jwt.secret.key}")
    private String secretKey;

    // JWT 서명에 사용할 비밀 키를 저장
    private Key key;

    // JWT 서명 알고리즘을 정의하는 열거형 변수 <HS256 (HMAC with SHA-256) 알고리즘>
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // 애플리케이션 시작 시 비밀 키를 디코딩하고 Key 객체로 변환하는 초기화 작업을 수행
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    /**
     *  Header 에서 Token 가져오기
     */
    // header 토큰을 가져오기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * JWT 생성
     */
    // 토큰 생성
    public String createToken(String username, UserRoleEnum role) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .claim(AUTHORIZATION_KEY, role)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    /**
     * JWT 검증
     */
    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    /**
     * JWT 에서 사용자 정보 가져오기
     */
    // 토큰에서 사용자 정보 가져오기
     public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * HttpServletRequest 에서 얻는 Token 으로 사용자아이디 반환하는 메서드
     * @param request
     * @return username From Token
     */
    public String getUsernameFromRequest(HttpServletRequest request){
//        TODO: private 으로 수정
         // 토큰 찾기
         String token = resolveToken(request);

         // 유효한 토큰인지 확인
         if( ! validateToken(token)){
             throw MemberEx.invalidToken();
         }
         
         // 토큰 반환
        String res = getUserInfoFromToken(token).getSubject();
         return getUserInfoFromToken(token).getSubject();
    }

}
