package org.raml.ramltopojo.extensions.jaxb;

import amf.client.model.domain.UnionShape;
import com.squareup.javapoet.*;
import org.junit.Before;
import org.junit.Test;
import org.raml.ramltopojo.EventType;
import org.raml.testutils.UnitTest;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created. There, you have it.
 */
public class JaxbUnionExtensionTest extends UnitTest {

    UnionShape unionTypeDeclaration;

    @Before
    public void before() {

        unionTypeDeclaration = (UnionShape) new UnionShape().withName("buildClass");
    }

    @Test
    public void className() {

        JaxbUnionExtension jaxb = new JaxbUnionExtension();
        ClassName typeName = ClassName.bestGuess("foo.Union");
        TypeName calculatedTypeName = jaxb.className(null, null, typeName, null);
        assertSame(typeName, calculatedTypeName);
    }

    @Test
    public void classCreated() {

        JaxbUnionExtension jaxb = new JaxbUnionExtension();
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder("my.BuiltClass");

        TypeSpec.Builder builder = jaxb.classCreated(null, unionTypeDeclaration, classBuilder, EventType.INTERFACE);
        TypeSpec buildClass = builder.build();

        TypeSpecAssert.assertThat(buildClass)
                .hasName("my.BuiltClass");
        AnnotationSpecAssert.assertThat(buildClass.annotations.get(0)).hasType(ClassName.get(XmlRootElement.class));

        assertEquals("\"buildClass\"", builder.build().annotations.get(0).members.get("name").get(0).toString());
        assertEquals("\"##default\"", builder.build().annotations.get(0).members.get("namespace").get(0).toString());
    }

    @Test
    public void implementationClassCreated() {

        JaxbUnionExtension jaxb = new JaxbUnionExtension();
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder("my.BuiltClass");

        TypeSpec.Builder builder = jaxb.classCreated(null, unionTypeDeclaration, classBuilder, EventType.IMPLEMENTATION);
        TypeSpec buildClass = builder.build();

        TypeSpecAssert.assertThat(buildClass)
                .hasName("my.BuiltClass");

        AnnotationSpecAssert.assertThat(buildClass.annotations.get(0)).hasType(ClassName.get(XmlAccessorType.class));
        assertEquals("javax.xml.bind.annotation.XmlAccessType.FIELD", builder.build().annotations.get(0).members.get("value").get(0).toString());

        AnnotationSpecAssert.assertThat(buildClass.annotations.get(1)).hasType(ClassName.get(XmlRootElement.class));
        assertEquals("\"buildClass\"", builder.build().annotations.get(1).members.get("name").get(0).toString());
        assertEquals("\"##default\"", builder.build().annotations.get(1).members.get("namespace").get(0).toString());
    }

    @Test
    public void anyFieldCreated() {
    }
}