package ir.man.spring.security.listener;

import ir.man.spring.security.model.Privilege;
import ir.man.spring.security.model.Role;
import ir.man.spring.security.repository.PrivilegeRepository;
import ir.man.spring.security.repository.RoleRepository;
import ir.man.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;

@Component
public class AppListener {

    boolean alreadySetup = false;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @EventListener
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) return;

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");

        createRolePrivilegeIfNotFound("ROLE_ADMIN", List.of(deletePrivilege));
        createRolePrivilegeIfNotFound("ROLE_STAFF", List.of(writePrivilege));
        createRolePrivilegeIfNotFound("ROLE_USER", List.of(readPrivilege));

        alreadySetup = true;

    }

    @Transient
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transient
    @Transactional
    Role createRolePrivilegeIfNotFound(String name, List<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
