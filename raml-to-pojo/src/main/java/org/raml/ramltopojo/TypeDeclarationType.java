package org.raml.ramltopojo;

import com.google.common.collect.ImmutableMap;
import org.raml.ramltopojo.enumeration.EnumerationTypeHandler;
import org.raml.v2.api.model.v10.datamodel.*;

import java.util.Map;

/**
 * Created. There, you have it.
 */
public enum TypeDeclarationType implements TypeHandlerFactory {

    /*
     private static Map<Class, Class<?>> scalarToType = ImmutableMap.<Class, Class<?>>builder()
      .put(IntegerTypeDeclaration.class, int.class)
      .put(BooleanTypeDeclaration.class, boolean.class)
      .put(DateTimeOnlyTypeDeclaration.class, Date.class)
      .put(TimeOnlyTypeDeclaration.class, Date.class)
      .put(DateTimeTypeDeclaration.class, Date.class).put(DateTypeDeclaration.class, Date.class)
      .put(NumberTypeDeclaration.class, BigDecimal.class)
      .put(StringTypeDeclaration.class, String.class).put(FileTypeDeclaration.class, File.class)
      .put(AnyTypeDeclaration.class, Object.class)
      .build();

  private static Map<String, Class<?>> stringScalarToType = ImmutableMap
      .<String, Class<?>>builder().put("integer", int.class).put("boolean", boolean.class)
      .put("date-time", Date.class).put("date", Date.class).put("number", BigDecimal.class)
      .put("string", String.class).put("file", File.class).build();

  // cheating: I know I only have one table for floats and ints, but the parser
  // should prevent problems.
  private static Map<String, Class<?>> properType = ImmutableMap.<String, Class<?>>builder()
      .put("float", float.class).put("double", double.class).put("int8", byte.class)
      .put("int16", short.class).put("int32", int.class).put("int64", long.class)
      .put("int", int.class).build();

  private static Map<String, Class<?>> properTypeObject = ImmutableMap.<String, Class<?>>builder()
      .put("float", Float.class).put("double", Double.class).put("int8", Byte.class)
      .put("int16", Short.class).put("int32", Integer.class).put("int64", Long.class)
      .put("int", Integer.class).build();

     */
    OBJECT {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }
    },
    ENUMERATION {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }
    },
    ARRAY {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    UNION {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    INTEGER {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    BOOLEAN {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    DATE {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    DATETIME {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    TIME_ONLY {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    DATETIME_ONLY {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    NUMBER {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    STRING {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {

            StringTypeDeclaration declaration = (StringTypeDeclaration) typeDeclaration;
            if ( declaration.enumValues().isEmpty() ) {
                return new EnumerationTypeHandler(declaration);
            } else {

                throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
            }
        }
    },
    ANY {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    },
    FILE {
        @Override
        public TypeHandler create(TypeDeclarationType type, TypeDeclaration typeDeclaration) {
            throw new IllegalArgumentException("can't handle " + typeDeclaration.getClass());
        }

    };

    private static Map<Class, TypeDeclarationType> scalarToType = ImmutableMap.<Class, TypeDeclarationType>builder()
            .put(ObjectTypeDeclaration.class, OBJECT)
            .put(ArrayTypeDeclaration.class, ARRAY)
            .put(UnionTypeDeclaration.class, UNION)
            .put(DateTimeOnlyTypeDeclaration.class, DATETIME_ONLY)
            .put(TimeOnlyTypeDeclaration.class, TIME_ONLY)
            .put(IntegerTypeDeclaration.class, INTEGER)
            .put(BooleanTypeDeclaration.class, BOOLEAN)
            .put(DateTimeOnlyTypeDeclaration.class, DATETIME_ONLY)
            .put(TimeOnlyTypeDeclaration.class, TIME_ONLY)
            .put(DateTimeTypeDeclaration.class, DATETIME)
            .put(DateTypeDeclaration.class, DATE)
            .put(NumberTypeDeclaration.class, NUMBER)
            .put(StringTypeDeclaration.class, STRING)
            .put(FileTypeDeclaration.class, FILE)
            .put(AnyTypeDeclaration.class, ANY)
            .build();

    public static TypeHandler fromRamlParser(TypeDeclaration typeDeclaration) {

        TypeDeclarationType typeDeclarationType = scalarToType.get(typeDeclaration.getClass());

        return typeDeclarationType.create(typeDeclarationType, typeDeclaration);
    }


}
