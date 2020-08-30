package org.oaknorth.graphql.server.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.oaknorth.graphql.server.entity.BankDetails;
import org.oaknorth.graphql.server.entity.BranchDetails;
import org.oaknorth.graphql.server.entity.Users;
import org.oaknorth.graphql.server.exception.BadTokenException;
import org.oaknorth.graphql.server.models.BankDetailsModel;
import org.oaknorth.graphql.server.models.BranchDetailsModel;
import org.oaknorth.graphql.server.models.Token;
import org.oaknorth.graphql.server.models.UserModel;
import org.oaknorth.graphql.server.security.CustomUserDetailService;
import org.oaknorth.graphql.server.service.BankService;
import org.oaknorth.graphql.server.service.BranchService;
import org.oaknorth.graphql.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.time.ZoneId;


@Slf4j
@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private BankService bankService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('BANKER')")
    public Users createUser(UserModel userModel){
        log.info("Inside create user mutation");
        Users users = Users.map(userModel);
        users.setUserType(Users.UserType.USER);
        users.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userService.createUser(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Users createBanker(UserModel userModel){
        log.info("Inside create banker mutation");
        Users users = Users.map(userModel);
        users.setUserType(Users.UserType.BANKER);
        users.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userService.createUser(users);
    }

    @PreAuthorize("hasAuthority('BANKER')")
    public BranchDetails createBranch(BranchDetailsModel branchDetailsModel){
        return branchService.createBranch(BranchDetails.map(branchDetailsModel));
    }


    @PreAuthorize("hasAuthority('BANKER')")
    public BankDetails createBankDetails(@Valid BankDetailsModel bankDetails){
        return bankService.createBank(bankDetails);
    }

    @PreAuthorize("isAnonymous()")
    public Token login(String email, String password) {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(email, password);
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credentials));
            String token = customUserDetailService.getToken(customUserDetailService.getCurrentUser());
            return new Token(token,customUserDetailService.getDecodedToken(token).orElseThrow(BadTokenException::new).
                    getExpiresAt().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());

    }
}
