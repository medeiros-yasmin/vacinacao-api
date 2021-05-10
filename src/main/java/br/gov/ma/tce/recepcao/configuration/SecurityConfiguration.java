package br.gov.ma.tce.recepcao.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.gov.ma.tce.recepcao.domain.repository.UsuarioRepository;
import br.gov.ma.tce.recepcao.domain.services.TokenService;
import br.gov.ma.tce.recepcao.domain.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserService usuarioService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	
	//autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.userDetailsService(usuarioService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	//autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		 http.authorizeRequests()
		 .antMatchers("/autenticacao").permitAll()
		 .anyRequest().authenticated()
		 .and().cors()
		 .and().csrf().disable()
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and() 
		 .addFilterBefore(new FiltroAutenticacao(tokenService, usuarioRepository),UsernamePasswordAuthenticationFilter.class);
		 

	}
	
	
	//recursos estáticos
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * System.out.println(new BCryptPasswordEncoder().encode("030303")); }
	 */
}
