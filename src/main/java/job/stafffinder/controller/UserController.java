package job.stafffinder.controller;

import job.stafffinder.model.User;
import job.stafffinder.service.MailService;
import job.stafffinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import static java.lang.String.format;

@RestController
@RequestMapping(value = "/users")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> registerUser(@RequestBody @Validated User user, UriComponentsBuilder uriBuilder, HttpServletRequest request) {

        if (userService.isExist(user)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        userService.save(user);
        mailService.notifyUserRegistred(user, request.getLocale());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return notFoundResponse();
        }
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    public ResponseEntity<String> notFoundResponse() {
        String jsonResponse = format("{\"status\":\"%s\", \"errorMessage\":\"%s\"}", HttpStatus.NOT_FOUND, "Not Found");
        return new ResponseEntity<String>(jsonResponse, HttpStatus.NOT_FOUND);
    }
}
