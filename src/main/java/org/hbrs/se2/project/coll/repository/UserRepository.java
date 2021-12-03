package org.hbrs.se2.project.coll.repository;

import org.hbrs.se2.project.coll.dtos.StudentUserDTO;
import org.hbrs.se2.project.coll.dtos.UserDTO;
import org.hbrs.se2.project.coll.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
/**
 * JPA-Repository für die Verwaltung von Autos (cars). Die Bezeichnung einer Methode
 * bestimmt dabei die Selektionsbedingung (den WHERE-Teil). Der Rückgabewert einer
 * Methode bestimmt den Projectionsbedingung (den SELECT-Teil).
 * Mehr Information über die Entwicklung von Queries in JPA:
 * https://www.baeldung.com/spring-data-jpa-projections
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    UserDTO findUserByIdAndPassword ( int userid, String password );

    UserDTO findUserByEmailAndPassword ( String email, String password );

    UserDTO findUserByEmail(String email);

    // SELECT *
    // FROM User p
    // WHERE p.userid = [StringValueOf( userid )]
    UserDTO findUserById ( int userid );


}