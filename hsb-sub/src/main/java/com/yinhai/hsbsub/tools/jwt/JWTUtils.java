package com.yinhai.hsbsub.tools.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author chenyl
 */
public class JWTUtils {
    private static final String SECRET = "YINHAI-HTTPGATEWAYSERVER-SECRET-SLOT";
    /**
     * token有效期one week
     */
    private static final long EXPIRE_TIME = 7*24*60*60*1000L;

    /**
     * 创建Token
      * @param identity 身份信息
     * @return 令牌
     */
    public static String buildToken(Identity identity) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRE_TIME);
        return JWT.create()
                .withClaim("userId",identity.getId())
                .withClaim("level", identity.getLevel())
                .withIssuedAt(now).withExpiresAt(exp)
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 校验token
     * @param token 令牌
     * @return 校验结果
     */
    public static boolean verity(String token){
        if(token == null || "".equals(token)){
            return false;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 获取角色信息
     * @param token 令牌
     * @return 角色权限等级
     */
    public static Integer getLevel(String token) {
        DecodedJWT decodedJwt = JWT.decode(token);
        Claim claim = decodedJwt.getClaim("level");
        return claim.asInt();
    }
}