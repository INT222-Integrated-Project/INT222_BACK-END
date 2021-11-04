package swst.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import swst.application.entities.UsernamesModels;
import swst.application.errorsHandlers.ExceptionFoundation;
import swst.application.errorsHandlers.ExceptionresponsesModel.EXCEPTION_CODES;
import swst.application.models.GetUserModel;
import swst.application.repositories.RolesRepository;
import swst.application.repositories.UsernameRepository;

@Service
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private final UsernameRepository usernameRepository;
	@Autowired
	private final RolesRepository rolesRepository;

	
	// [ listOnlyAdmin ]
	
	// [ assignRole ]
	public GetUserModel assignRole(GetUserModel getUser) {
		UsernamesModels assignTo = usernameRepository.findByUserName(getUser.getUserName());
		if (assignTo == null) {
			throw new ExceptionFoundation(EXCEPTION_CODES.SEARCH_NOT_FOUND,
					"[ NOT EXIST] This user is not exist in our database.");
		}
		assignTo.setRole(rolesRepository.findByroleName(getUser.getUserRole()));
		usernameRepository.save(assignTo);
		return getUser;
	}

}
