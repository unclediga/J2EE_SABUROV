package ru.unclediga.saburov.register.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.unclediga.saburov.register.domain.MarriageCertificate;

import java.util.List;

@Repository
public interface MarriageDao extends JpaRepository<MarriageCertificate, Long> {

    List<MarriageCertificate> findByNumber(String number);
    List<MarriageCertificate> findByNumberContaining(String number);
    List<MarriageCertificate> findByNumberNoParam(String number);
    List<MarriageCertificate> findByNumberParam(@Param("number") String number);
}