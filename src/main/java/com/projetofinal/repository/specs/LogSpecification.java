package com.projetofinal.repository.specs;

import com.projetofinal.data.Log;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LogSpecification implements Specification<Log> {

    List<SearcheCriteria> list;

    public LogSpecification() {
        list = new ArrayList<>();
    }

    public void add(SearcheCriteria searcheCriteria) {
        list.add(searcheCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        for (SearcheCriteria searcheCriteria : list) {
            if (searcheCriteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(criteriaBuilder.equal(root.get(searcheCriteria.getKey()),
                        searcheCriteria.getValue()));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
