package job.stafffinder.controller;

import job.stafffinder.model.User;
import job.stafffinder.service.MailService;
import job.stafffinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;

import static java.lang.String.format;

@RestController
@RequestMapping(value = "/users", produces = "application/json;charset=UTF-8")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> registerUser(@RequestBody @Validated User user, UriComponentsBuilder uriBuilder, Locale locale) {

        if (userService.isExist(user)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.save(user);
        mailService.notifyUserRegistered(user, locale);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
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
