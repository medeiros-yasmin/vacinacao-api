package br.gov.ma.tce.recepcao.domain.services;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.gov.ma.tce.recepcao.domain.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	
	public String gerarToken(Authentication authenticate){


		Usuario logado = (Usuario) authenticate.getPrincipal();
		Date hoje =  new Date();
		Date dataExpiracao = new Date(hoje.getTime() + 3600000 * 12);
		
		//os dados do usuário vão dentro do token, a aplicação cliente tem que virar para
		//decodificar o token e recuperar o usuário
		

		return Jwts.builder()
				.setIssuer("recepcaoApi")
				.setSubject(logado.getUsuarioId().toString())
				.claim("usuario",logado )
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, "dzPw@").compact();
	}




	public boolean isValido(String token) {
		try {
			@SuppressWarnings("unused")
			Jws<Claims> claims = Jwts.parser().setSigningKey("dzPw@").parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}



	public Integer getIdUsuario(String token) {
		
		Claims claims = Jwts.parser().setSigningKey("dzPw@").parseClaimsJws(token).getBody();
	    return Integer.parseInt(claims.getSubject());
	}

}
