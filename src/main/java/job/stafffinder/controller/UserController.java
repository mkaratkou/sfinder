package job.stafffinder.controller;

import job.stafffinder.model.User;
import job.stafffinder.service.MailService;
import job.stafffinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> registerUser(@RequestBody User user, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        if (userService.isExist(user)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        userService.save(user);
        mailService.notifyUserRegistred(user, request.getLocale());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return notFoundResponse();
        }
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    public ResponseEntity<String> notFoundResponse() {
        return new ResponseEntity<String>(String.format(
                "{\"status\":\"%s\", \"errorMessage\":\"%s\"}", HttpStatus.NOT_FOUND, "Not Found"),
                HttpStatus.NOT_FOUND);
    }
}
