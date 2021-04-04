package cn.testlove.database.security;

import cn.testlove.database.util.JwtUtils;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * @author admin
 * 自定义authentication中所需要的信息
 */
@Getter
public class TokenWebAuthenticationDetails extends WebAuthenticationDetails {
    private String token;
    private boolean tokenExpired;
    private boolean tokenExists;
    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public TokenWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        Optional<String> tokenOptional = Optional.ofNullable(request.getHeader("token")) ;


    }
}
