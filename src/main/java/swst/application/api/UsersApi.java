package swst.application.api;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import swst.application.authenSecurity.TokenUtills;
import swst.application.controllers.ProductOrderController;
import swst.application.controllers.ProductsController;
import swst.application.controllers.UserController;
import swst.application.entities.OrderDetail;
import swst.application.entities.OrderStatus;
import swst.application.entities.Orders;
import swst.application.entities.UsernamesModels;
import swst.application.models.ActionResponseModel;
import swst.application.models.LoginResponseModel;
import swst.application.repositories.OrderDetailRepository;
import swst.application.repositories.OrderStatusRepository;
import swst.application.repositories.OrdersRepository;
import swst.application.repositories.UsernameRepository;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersApi {

	@Autowired
	private final ProductsController productsRESTcontroller;
	@Autowired
	private final UsernameRepository usernameRepository;
	@Autowired
	private final UserController userController;
	@Autowired
	private final ProductOrderController productOrderController;
	@Autowired
	private final OrdersRepository ordersRepository;
	@Autowired
	private final OrderStatusRepository orderStatusRepository;
	@Autowired
	private final OrderDetailRepository orderDetailRepository;

	// [ getMyprofile ] Will return a profile of that user.
	@GetMapping("/myprofile")
	public ResponseEntity<UsernamesModels> getMyprofile(HttpServletRequest request) {
		return ResponseEntity.ok().body(usernameRepository.findByUserName(TokenUtills.getUserNameFromToken(request)));
	}

	// [ userLogOut ] Will remove a token from user.
	@GetMapping("/auth/logout")
	public ResponseEntity<LoginResponseModel> userLogOut(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader(HttpHeaders.AUTHORIZATION, "");
		return ResponseEntity.ok().body(new LoginResponseModel("User was here", ""));
	}

	// [ addOrders ]
	@PostMapping("/addOrder")
	public ResponseEntity<Orders> addOrder(@RequestPart Orders newOrders, HttpServletRequest request) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/addOrder").toString());
		return ResponseEntity.created(uri).body(productOrderController.addOrder(request, newOrders));
	}

	// [ cancleUserOrder ]
}
