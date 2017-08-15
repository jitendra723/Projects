package com.project.queryBuilder;



import org.apache.commons.lang3.StringUtils;

import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;
import com.project.models.User;



public class UserPredicate {
	 
    private SearchCriteria criteria;
    
    
 
    public UserPredicate(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}



	public BooleanExpression getPredicate() {
        PathBuilder<User> entityPath = new PathBuilder<>(User.class, "user");
        
        if (StringUtils.isNumeric(criteria.getValue().toString())) {
        	
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.gt(value);
                case "<":
                    return path.lt(value);
            }
        } 
        else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }




}