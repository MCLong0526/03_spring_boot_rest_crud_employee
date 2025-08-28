package com.michael.cruddemo.dao;

import com.michael.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll(){
//        return entityManager.createQuery("from Employee", Employee.class).getResultList();
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        List<Employee> employees = theQuery.getResultList();

        return employees;

    }

    @Override
    public Employee findById(int theId) {
        return entityManager.createQuery("from Employee WHERE id=:theId", Employee.class)
                .setParameter("theId", theId)
                .getSingleResult();
    }

    @Override
    public Employee save(Employee theEmployee) {
        return entityManager.merge(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class, theId);
        entityManager.remove(theEmployee);
    }

    @Override
    public Employee update(Employee theEmployee) {
        return entityManager.merge(theEmployee);
    }


}

