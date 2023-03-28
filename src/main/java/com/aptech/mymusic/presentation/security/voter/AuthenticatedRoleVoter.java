package com.aptech.mymusic.presentation.security.voter;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

public class AuthenticatedRoleVoter implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public int vote(Authentication authentication, @NotNull FilterInvocation invocation, Collection<ConfigAttribute> attributes) {

        System.out.println("\n vote ==== ");
        System.out.println(authentication);
        System.out.println(invocation);
        System.out.println(attributes);

        for (ConfigAttribute attribute :attributes){
            System.out.println(attribute.getClass().getSimpleName());
        }

//        if (authentication == null
//                || attributes == null
//                || authentication.getAuthorities() == null
//                || !authentication.isAuthenticated()) {
//            return ACCESS_DENIED;
//        }
//
//        for (GrantedAuthority authority : authentication.getAuthorities()) {
//            for (ConfigAttribute attribute : attributes) {
//                if (Objects.equals(hasAuthority(authority.getAuthority()), attribute.toString())) {
//                    System.out.println("Granted");
//                    return ACCESS_GRANTED;
//                }
//            }
//        }

        return ACCESS_ABSTAIN;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }
}
