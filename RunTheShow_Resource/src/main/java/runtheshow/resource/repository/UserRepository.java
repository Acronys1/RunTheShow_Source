package runtheshow.resource.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	// liste des rôles d'un utilisateur identifié par son id
	@Query("select ur.role from UserRole ur where ur.user.id=?1")
	Iterable<Role> getRoles(long id);
        
        // liste des rôles d'un utilisateur identifié par son nom
	@Query("select ur.role from UserRole ur where ur.user.login=?1")
	Iterable<Role> getRolesByName(String name);

	// liste des rôles d'un utilisateur identifié par son login et son mot de passe
	@Query("select ur.role from UserRole ur where ur.user.login=?1 and ur.user.password=?2")
	Iterable<Role> getRoles(String login, String password);

	// recherche d'un utilisateur via son login
	User findUserByLogin(String login);

        //Liste des user ayant un profil artiste avec un nom d'artiste contenant le pattern motCherche
        // !!! Pas de LIMIT en JPA.........
        @Query("SELECT ur.user "
                + "FROM UserRole ur "
                + "WHERE ur.role.name = 'ROLE_ARTISTE' "
                + "AND ur.user.nomArtiste LIKE %?1%")
        List<User> findUserArtisteByMotCle(String motCherche);
        
       
        @Query("SELECT ur "
                + "FROM User ur "
                + "WHERE ur.id IN :ids")
        public List<User> findUserArtisteByIds(@Param("ids") Set<Long> ids);
}
