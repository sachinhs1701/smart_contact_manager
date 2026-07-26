package com.scm.scm10.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.scm10.entity.Contacts;

@Repository
public interface ContactsRepo extends JpaRepository<Contacts, String>{

	@Query("SELECT c FROM Contacts c WHERE c.user.id= :userid")
	List<Contacts> findByUserId(@Param("userid") String userid);
}
