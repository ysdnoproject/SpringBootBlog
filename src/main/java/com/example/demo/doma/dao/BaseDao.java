package com.example.demo.doma.dao;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;
import org.seasar.doma.jdbc.entity.EntityType;
import org.seasar.doma.jdbc.entity.EntityTypeFactory;
import org.springframework.core.GenericTypeResolver;

import java.util.Optional;

// https://github.com/domaframework/doma/issues/185
@SuppressWarnings("unchecked")
public interface BaseDao<T> {
    default Optional<T> findById(Long id) {
        // https://stackoverflow.com/a/9202329/9281649
        Class<T> clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDao.class);
        Config config = Config.get(this);
        SelectBuilder builder = SelectBuilder.newInstance(config);
        EntityType<T> entityType = EntityTypeFactory.getEntityType(clazz, config.getClassHelper());
        builder.sql("SELECT * FROM " + entityType.getTableName() + " WHERE id=" + id);
        return builder.getOptionalEntitySingleResult(clazz);
    }
}
