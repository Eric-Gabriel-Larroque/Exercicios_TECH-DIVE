package projeto.security;

import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.env.DefaultEnvironment;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;

import javax.inject.Inject;
import javax.servlet.ServletContext;

public class CustomEnvironmentLoaderListener extends EnvironmentLoaderListener {

    @Inject
    private ShiroRealm shiroRealm;

    @Override
    protected WebEnvironment createEnvironment(ServletContext pServletContext) {
        WebEnvironment environment = super.createEnvironment(pServletContext);
        RealmSecurityManager rsm = (RealmSecurityManager) environment.getSecurityManager();
        shiroRealm.setCredentialsMatcher(new SimpleCredentialsMatcher());
        rsm.setRealm(shiroRealm);
        ((DefaultEnvironment) environment).setSecurityManager(rsm);
        return environment;
    }


}
