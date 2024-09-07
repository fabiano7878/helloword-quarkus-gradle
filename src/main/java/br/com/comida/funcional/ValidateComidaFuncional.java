package br.com.comida.funcional;

import br.com.comida.dto.ComidaDTO;
import br.com.comida.dto.ProdutoDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;

@FunctionalInterface
public interface ValidateComidaFuncional {
    boolean isNullComida(ComidaDTO comidaDTO);

    default boolean isNullOrBlankMandatoryies(ComidaDTO comidaDTO, Validator validator){
        Set<ConstraintViolation<ComidaDTO>> violations = validator.validate(comidaDTO);
        StringBuilder sb = new StringBuilder();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<ComidaDTO> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
                System.out.println(sb);
                return true;
            }
        }
        return false;
    }
}
