package com.berru.app.cabbookingapplication.service.base;

import com.berru.app.cabbookingapplication.rsql.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class GenericRsqlService<E, D> {

    private final JpaSpecificationExecutor<E> repository;
    private final Function<E, D> mapper;

    protected GenericRsqlService(JpaSpecificationExecutor<E> repository, Function<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<D> searchByRsql(String query) {
        RSQLParser parser = new RSQLParser();
        Node rootNode = parser.parse(query);

        CustomRsqlVisitor<E> visitor = new CustomRsqlVisitor<>();
        Specification<E> spec = rootNode.accept(visitor);

        List<E> entities = repository.findAll(spec);
        return entities.stream().map(mapper).collect(Collectors.toList());
    }
}
