package org.oaknorth.graphql.server.resolvers;

import graphql.language.ScalarTypeDefinition;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateTimeScalarType extends GraphQLScalarType {
    public DateTimeScalarType() {
        super("LocalDateTime", "Scalar LocalDateTime", new Coercing<LocalDateTime,String>() {
            @Override
            public String serialize(Object o)  {
                return ((LocalDateTime) o).toString();
            }

            @Override
            public LocalDateTime parseValue(Object o) {
                return parseLiteral(o);
            }

            @Override
            public LocalDateTime parseLiteral(Object o)  {
                return LocalDateTime.parse(((StringValue) o).getValue());
            }
        });
    }
}
