package org.raml.ramltopojo;

import com.google.common.base.Optional;
import com.squareup.javapoet.TypeName;
import org.raml.v2.api.model.v10.datamodel.TypeDeclaration;

/**
 * Created. There, you have it.
 */
public class RamlToPojoImpl implements RamlToPojo {
    private final TypeFinder typeFinder;
    private final GenerationContextImpl generationContext;

    public RamlToPojoImpl( TypeFinder typeFinder, GenerationContextImpl generationContext) {

        this.typeFinder = typeFinder;
        this.generationContext = generationContext;
    }

    @Override
    public ResultingPojos buildPojos() {

        ResultingPojos resultingPojos = new ResultingPojos(generationContext);

        for (TypeDeclaration typeDeclaration : typeFinder.findTypes(generationContext.api())) {

            TypeDeclarationType.calculateTypeName(typeDeclaration.name(), typeDeclaration, generationContext, EventType.INTERFACE);
        }

        for (TypeDeclaration typeDeclaration : typeFinder.findTypes(generationContext.api())) {

            Optional<CreationResult> spec = TypeDeclarationType.createType(typeDeclaration, generationContext);
            if ( spec.isPresent() ) {
                resultingPojos.addNewResult(spec.get());
            }
        }

        return resultingPojos;
    }

    @Override
    public ResultingPojos buildPojo(TypeDeclaration typeDeclaration) {

        ResultingPojos resultingPojos = new ResultingPojos(generationContext);

        Optional<CreationResult> spec = TypeDeclarationType.createType(typeDeclaration, generationContext);
        if (spec.isPresent() ) {
            resultingPojos.addNewResult(spec.get());
        }

        return resultingPojos;
    }

    @Override
    public ResultingPojos buildPojo(String suggestedJavaName, TypeDeclaration typeDeclaration) {

        ResultingPojos resultingPojos = new ResultingPojos(generationContext);

        Optional<CreationResult> spec = TypeDeclarationType.createNamedType(suggestedJavaName, typeDeclaration, generationContext);
        if ( spec.isPresent() ) {
            resultingPojos.addNewResult(spec.get());
        }

        return resultingPojos;
    }

    @Override
    public TypeName fetchType(String suggestedName, TypeDeclaration typeDeclaration) {


        return TypeDeclarationType.calculateTypeName(suggestedName, typeDeclaration, generationContext, EventType.INTERFACE);
    }

    public boolean isInline(TypeDeclaration typeDeclaration) {

        return TypeDeclarationType.isNewInlineType(typeDeclaration);
    }
}
