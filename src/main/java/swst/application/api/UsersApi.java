package swst.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import swst.application.authenSecurity.UserNameService;
import swst.application.controllers.controller.ModelController;
import swst.application.controllers.controller.ProductsController;
import swst.application.controllers.controller.PublicUserController;
import swst.application.entities.Products;
import swst.application.entities.UsernamesModels;
import swst.application.repositories.BrandsRepository;
import swst.application.repositories.ColorsRepository;
import swst.application.repositories.UsernameRepository;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersApi {

	@Autowired
	private final ProductsController productsRESTcontroller;

	@GetMapping("/myprofile")
	public ResponseEntity<UsernamesModels> getMyprofile() {
		return null;
	}

}
