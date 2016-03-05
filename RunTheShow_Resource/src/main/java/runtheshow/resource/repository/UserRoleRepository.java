package runtheshow.resource.repository;

import org.springframework.data.repository.CrudRepository;
import runtheshow.resource.entities.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
