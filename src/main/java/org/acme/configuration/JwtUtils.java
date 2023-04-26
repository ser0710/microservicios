package org.acme.configuration;

import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtils {

    public static boolean validarToken(String headerAuth, String secret) {
        if (headerAuth == null || !headerAuth.startsWith("Bearer ")) {
            // El token no está presente o no está en el formato adecuado
            return false;
        }

        String token = headerAuth.substring(7); // Extraer la cadena del token del encabezado
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            // Si no se lanza ninguna excepción, el token es válido
            return true;
        } catch (Exception ex) {
            // El token es inválido
            return false;
        }
    }

}