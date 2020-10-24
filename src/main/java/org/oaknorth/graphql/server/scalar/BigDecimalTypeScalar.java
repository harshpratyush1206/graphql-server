package org.oaknorth.graphql.server.scalar;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class BigDecimalTypeScalar extends GraphQLScalarType {
    public BigDecimalTypeScalar() {
        super("BigDecimal", "Scalar BigDecimal", new Coercing<BigDecimal,String>() {
            @Override
            public String serialize(Object o)  {
                return o.toString();
            }

            @Override
            public BigDecimal parseValue(Object o) {
                return parseLiteral(o);
            }

            @Override
            public BigDecimal parseLiteral(Object o)  {
                return new BigDecimal(o.toString());
            }
        });
    }
}