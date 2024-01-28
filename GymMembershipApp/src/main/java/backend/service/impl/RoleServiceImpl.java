package backend.service.impl;

import backend.domain.Role;
import backend.repository.RoleRepository;
import backend.service.RoleService;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(Role role) throws Exception {
        roleRepository.add(role);
    }

    @Override
    public void update(Role role) throws Exception {
        roleRepository.update(role);
    }

    @Override
    public void delete(Role role) throws Exception {
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() throws Exception {
        return roleRepository.getAll();
    }
}
