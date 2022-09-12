package ir.payeshgaran.project1.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.payeshgaran.project1.dto.AccountDTO;
import ir.payeshgaran.project1.mapper.AccountMapper;
import ir.payeshgaran.project1.model.Account;
import ir.payeshgaran.project1.model.Person;
import ir.payeshgaran.project1.service.implementation.PersonServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {



    @Autowired
    private PersonServiceImplementation personService;

    @GetMapping("/yourAccounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List persons = personService.getByUsername(getUsernameByToken(request, response));

        List<Account> accounts = ((Person) persons.get(0)).getAccounts();

        AccountMapper accountMapper = new AccountMapper();

        return ResponseEntity.ok().body(accountMapper.toDTOs(accounts));
    }

    @GetMapping("/allAccountsBalance")
    public ResponseEntity<Double> getAllAccountsBalance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsernameByToken(request, response);
        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        return ResponseEntity.ok().body(personService.getAllAccountBalance(person));
    }


    public static String getUsernameByToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = "";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                username = decodedJWT.getSubject();
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> errors = new HashMap<>();
                errors.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);
            }
        } else {
            throw new RuntimeException("You don't have access in here");
        }
        return username;
    }
}