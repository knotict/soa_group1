package hathome.authentication.authen;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mimi on 4/7/2017.
 */
public class JWTAuthenticationService {

    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10; // in milliseconds, 10 days
    public static final String SECRET = "aGF0aG9tQXV0aA==";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    /**
     * Parse token to authenticated user
     * @param request
     * @return
     */
    public User parseToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if(token != null) {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX + " ", ""));

            // Parse token
            Claims body = claims.getBody();

            String email = body.getSubject();
            Integer id = (Integer) body.get("id");
            String address = (String) body.get("address");

            if(email != null) {
                return new User(id, email, address);
            }
        }

        return null;
    }

}