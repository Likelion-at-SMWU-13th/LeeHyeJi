package org.springframework.samples.petclinic.db;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.model.YearlyRevenue;

import java.util.List;

/**
 * Query reports of revenue.
 */
public interface RevenueRepository extends Repository<Visit, Integer> {

    @Query("Select new org.springframework.samples.petclinic.model.YearlyRevenue(YEAR(v.date), sum(v.cost)) " +
        "from Visit v " +
        "group by YEAR(v.date)")
    List<YearlyRevenue> listYearlyRevenue();

    void save(Visit visit) throws DataAccessException;

}
