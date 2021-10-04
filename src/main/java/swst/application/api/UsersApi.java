package swst.application.api;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;
import swst.application.authenSecurity.TokenUtills;
import swst.application.controllers.controller.ProductsController;
import swst.application.entities.UsernamesModels;
import swst.application.repositories.UsernameRepository;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersApi {

	@Autowired
	private final ProductsController productsRESTcontroller;
	@Autowired
	private final UsernameRepository usernameRepository;

	// [ getMyprofile ] Will return a profile of that user.
	@GetMapping("/myprofile")
	public ResponseEntity<UsernamesModels> getMyprofile(HttpServletRequest request) {
		String authenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		UsernamesModels getThisUser = new UsernamesModels();
		if (authenHeader != null && authenHeader.startsWith("Bearer ")) {
			String token = authenHeader.substring("Bearer ".length());
			JWTVerifier verifier = JWT.require(TokenUtills.getAlgorithm()).build();
			DecodedJWT decodedJWT = verifier.verify(token);
			String userName = decodedJWT.getSubject();
			getThisUser = usernameRepository.findByUserName(userName);
		}
		return ResponseEntity.ok().body(getThisUser);
	}

}
