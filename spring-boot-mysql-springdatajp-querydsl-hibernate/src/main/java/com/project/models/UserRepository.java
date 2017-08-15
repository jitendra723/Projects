package com.project.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.StringPath;

/*public interface MyUserRepository extends JpaRepository<User, Long>,
 QueryDslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
 @Override
 default public void customize(QuerydslBindings bindings, QUser root) {
 bindings.bind(String.class)
 .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);


 bindings.excluding(root.email);
 }
 }*/

public interface UserRepository extends JpaRepository<User, Long>,
		QueryDslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
	@Override
	default public void customize(QuerydslBindings bindings, QUser root) {
		bindings.bind(String.class).first(
				new SingleValueBinding<StringPath, String>() {

					@Override
					public Predicate bind(StringPath path, String value) {
						return path.containsIgnoreCase(value);
					}
				});

		bindings.excluding(root.email);
	}
}