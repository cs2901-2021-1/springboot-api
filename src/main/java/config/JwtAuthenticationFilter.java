package config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static config.Constants.HEADER_STRING;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        if (header != null) {
            authToken = header;
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (ExpiredJwtException e) {
                logger.warn("Token expirado", e);
            } catch(Exception e){
                logger.error("Error en la autenticacion");
                chain.doFilter(req, res);
                return;
            }
        } else {
            logger.warn("error con el token");
        }
        // ENCONTRAR LA FORMA DE QUE EN VEZ DE ARROJAR CODE 500 GENERE UN  NUEVO TOKEN

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var userDetails = userDetailsService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(authToken, userDetails))) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("usuario autenticado " + username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                logger.warn("token invalido "+username);
            }
        }

        chain.doFilter(req, res);
    }
}
