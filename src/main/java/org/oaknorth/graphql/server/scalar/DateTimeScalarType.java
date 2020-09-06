package org.oaknorth.graphql.server.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.oaknorth.graphql.server.security.SecurityUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
public class DateTimeScalarType extends GraphQLScalarType {
    public DateTimeScalarType() {
        super("LocalDateTime", "Scalar LocalDateTime", new Coercing<LocalDateTime,String>() {
            @Override
            public String serialize(Object o)  {
                return (((LocalDateTime) o).atZone(ZoneOffset.UTC)).
                        withZoneSameInstant(ZoneId.of(SecurityUtil.getUserZone())).
                        toLocalDateTime().toString();
            }

            @Override
            public LocalDateTime parseValue(Object o) {
                return parseLiteral(o);
            }

            @Override
            public LocalDateTime parseLiteral(Object o)  {
                return LocalDateTime.parse(((StringValue) o).getValue()).atZone(ZoneId.of(SecurityUtil.getUserZone())).
                        withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
            }
        });
    }
}
