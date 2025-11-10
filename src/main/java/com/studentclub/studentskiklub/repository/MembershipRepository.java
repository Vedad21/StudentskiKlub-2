package com.studentclub.studentskiklub.repository;


import com.studentclub.studentskiklub.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    // Pronađi sva članstva za određenog studenta
    List<Membership> findByStudentId(Long studentId);

    // Pronađi članstva po statusu
    List<Membership> findByStatus(String status);
}
