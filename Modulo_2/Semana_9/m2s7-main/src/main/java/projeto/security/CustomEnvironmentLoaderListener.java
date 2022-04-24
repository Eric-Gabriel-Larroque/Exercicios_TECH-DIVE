package projeto.security;

import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;

import javax.inject.Inject;
import javax.servlet.ServletContext;

public class CustomEnvironmentLoaderListener extends EnvironmentLoaderListener {

    @Inject
    private ShiroRealm shiroRealm;

    /*
        Aqui é criado um ambiente web (já que estamos numa aplicação web) e em seguida pegamos o SecurityManager criado
        pelo Apache Shiro. Ao fazer isso, pegamos o nosso ShiroRealm (que injetamos acima) e setamos um novo SimpleCredentialsMatcher,
        pois esse cara não é criado automaticamente no nosso Realm. Após fazer isso, inserimos nosso ShiroRealm dentro do
        SecurityManager, pois esse é o cara que gerencia todas as coisas relacionadas a segurança do sistema.
        Por último, pegamos o ambiente web "environment" e inserimos nosso SecurityManager para dizer que ele é quem
        cuidará de toda a segurança.
    */
    @Override
    protected WebEnvironment createEnvironment(ServletContext pServletContext) {
        WebEnvironment environment = super.createEnvironment(pServletContext);
        RealmSecurityManager rsm = (RealmSecurityManager) environment.getSecurityManager();
        shiroRealm.setCredentialsMatcher(new SimpleCredentialsMatcher());
        rsm.setRealm(shiroRealm);
        ((DefaultWebEnvironment) environment).setSecurityManager(rsm);
        return environment;
    }
}