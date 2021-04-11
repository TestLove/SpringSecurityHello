package cn.testlove.database.security.authentication;

import cn.testlove.database.entity.TokenModel;
import cn.testlove.database.mapper.RoleMapper;
import cn.testlove.database.util.SpringBeanUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * @author admin
 * 使用token时的验证信息
 */
@Getter
@Setter
public class TokenAuthentication extends AbstractAuthenticationToken implements Authentication {
    private TokenModel tokenModel;
//    @Autowired
    RoleMapper roleMapper;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public TokenAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    /**
     * Set by an <code>AuthenticationManager</code> to indicate the authorities that the
     * principal has been granted. Note that classes should not rely on this value as
     * being valid unless it has been set by a trusted <code>AuthenticationManager</code>.
     * <p>
     * Implementations should ensure that modifications to the returned collection array
     * do not affect the state of the Authentication object, or use an unmodifiable
     * instance.
     * </p>
     *
     * @return the authorities granted to the principal, or an empty collection if the
     * token has not been authenticated. Never null.
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        roleMapper = (RoleMapper) SpringBeanUtils.getBean("roleMapper");
        System.out.println("roleMapper"+roleMapper);
        System.out.println("this.tokenModel"+this.tokenModel);
        System.out.println("super.getAuthorities()"+super.getAuthorities());
        System.out.println("roleMapper.selectRoleNameByRoleId(tokenModel.getRoleId())"+roleMapper.selectRoleNameByRoleId(tokenModel.getRoleId()));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleMapper.selectRoleNameByRoleId(tokenModel.getRoleId())));
        return authorities;
    }

    /**
     * The credentials that prove the principal is correct. This is usually a password,
     * but could be anything relevant to the <code>AuthenticationManager</code>. Callers
     * are expected to populate the credentials.
     *
     * @return the credentials that prove the identity of the <code>Principal</code>
     */
    @Override
    public Object getCredentials() {
        return tokenModel.getToken();
    }

    /**
     * Stores additional details about the authentication request. These might be an IP
     * address, certificate serial number etc.
     *
     * @return additional details about the authentication request, or <code>null</code>
     * if not used
     */
    @Override
    public Object getDetails() {
        return super.getDetails();
    }

    /**
     * The identity of the principal being authenticated. In the case of an authentication
     * request with username and password, this would be the username. Callers are
     * expected to populate the principal for an authentication request.
     * <p>
     * The <tt>AuthenticationManager</tt> implementation will often return an
     * <tt>Authentication</tt> containing richer information as the principal for use by
     * the application. Many of the authentication providers will create a
     * {@code UserDetails} object as the principal.
     *
     * @return the <code>Principal</code> being authenticated or the authenticated
     * principal after authentication.
     */
    @Override
    public Object getPrincipal() {
        return null;
    }

    /**
     * Used to indicate to {@code AbstractSecurityInterceptor} whether it should present
     * the authentication token to the <code>AuthenticationManager</code>. Typically an
     * <code>AuthenticationManager</code> (or, more often, one of its
     * <code>AuthenticationProvider</code>s) will return an immutable authentication token
     * after successful authentication, in which case that token can safely return
     * <code>true</code> to this method. Returning <code>true</code> will improve
     * performance, as calling the <code>AuthenticationManager</code> for every request
     * will no longer be necessary.
     * <p>
     * For security reasons, implementations of this interface should be very careful
     * about returning <code>true</code> from this method unless they are either
     * immutable, or have some way of ensuring the properties have not been changed since
     * original creation.
     *
     * @return true if the token has been authenticated and the
     * <code>AbstractSecurityInterceptor</code> does not need to present the token to the
     * <code>AuthenticationManager</code> again for re-authentication.
     */
    @Override
    public boolean isAuthenticated() {
        return super.isAuthenticated();
    }

    /**
     * See {@link #isAuthenticated()} for a full description.
     * <p>
     * Implementations should <b>always</b> allow this method to be called with a
     * <code>false</code> parameter, as this is used by various classes to specify the
     * authentication token should not be trusted. If an implementation wishes to reject
     * an invocation with a <code>true</code> parameter (which would indicate the
     * authentication token is trusted - a potential security risk) the implementation
     * should throw an {@link IllegalArgumentException}.
     *
     * @param isAuthenticated <code>true</code> if the token should be trusted (which may
     *                        result in an exception) or <code>false</code> if the token should not be trusted
     * @throws IllegalArgumentException if an attempt to make the authentication token
     *                                  trusted (by passing <code>true</code> as the argument) is rejected due to the
     *                                  implementation being immutable or implementing its own alternative approach to
     *                                  {@link #isAuthenticated()}
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
           super.setAuthenticated(isAuthenticated);
    }

    /**
     * Returns the name of this principal.
     *
     * @return the name of this principal.
     */
    @Override
    public String getName() {
        return null;
    }
}
