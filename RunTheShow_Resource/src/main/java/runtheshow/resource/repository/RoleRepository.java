package runtheshow.resource.repository;

import org.springframework.data.repository.CrudRepository;
import runtheshow.resource.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	// recherche d'un rôle via son nom
	Role findRoleByName(String name);

}
