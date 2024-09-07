package br.com.comida.funcional;

import br.com.comida.dto.ProdutoDTO;
import br.com.comida.entity.Produto;
import br.com.comida.record.ProdutoRecord;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Objects;
import java.util.Set;

@FunctionalInterface
public interface ValidateProdutoFuncional<T> {
    boolean isNullProduto(T t);

    default boolean isNullProduto(Produto p){
        return Objects.isNull(p);
    }

   default boolean isNullOrBlankMandatoryies(ProdutoDTO produtoDTO, Validator validator) {
       Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produtoDTO);
       StringBuilder sb = new StringBuilder();
       if (!violations.isEmpty()) {
           for (ConstraintViolation<ProdutoDTO> violation : violations) {
               sb.append(violation.getMessage()).append("\n");
               System.out.println(sb);
               return true;
           }
       }
       return false;
   }
}
