package com.aptech.mymusic.presentation.security.voter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.util.Assert;

import java.util.Collection;

public class CustomWebExpressionVoter extends WebExpressionVoter {

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public int vote(@NotNull Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> attributes) {
        Assert.notNull(authentication, "authentication must not be null");
        Assert.notNull(filterInvocation, "filterInvocation must not be null");
        Assert.notNull(attributes, "attributes must not be null");
        ConfigAttribute configAttribute = findConfigAttribute(attributes);
        if (configAttribute == null) {
            this.logger.trace("Abstained since did not find a config attribute of instance WebExpressionConfigAttribute");
            return ACCESS_ABSTAIN;
        }

        System.out.println("\n\n----- vote ------");
        System.out.println(authentication.getAuthorities());
        System.out.println(filterInvocation);
        System.out.println(filterInvocation.getClass());
        System.out.println(configAttribute);
        System.out.println(configAttribute.getClass());

        return super.vote(authentication, filterInvocation, attributes);
    }

    @Nullable
    private ConfigAttribute findConfigAttribute(Collection<ConfigAttribute> attributes) {
        for (ConfigAttribute attribute : attributes) {
            if (attribute != null) {
                return attribute;
            }
        }
        return null;
    }
}
