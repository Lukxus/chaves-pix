package com.pix.chaves.rest.dto.request;


import com.pix.chaves.domain.model.ChavePix;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ChavePixFilterSpecification implements Specification<ChavePix> {
    private final ChavePixFilter filter;

    @Override
    public Predicate toPredicate(Root<ChavePix> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getTipoChave() != null) {
            predicates.add(cb.equal(root.get("tipoChave"), filter.getTipoChave()));
        }

        if (filter.getValorChavePix() != null && !filter.getValorChavePix().isBlank()) {
            predicates.add(cb.equal(root.get("valorChave"), filter.getValorChavePix()));
        }

        if (filter.getTipoConta() != null) {
            predicates.add(cb.equal(root.get("tipoConta"), filter.getTipoConta()));
        }

        if (filter.getNumeroAgencia() != null && !filter.getNumeroAgencia().isBlank()) {
            predicates.add(cb.equal(root.get("numeroAgencia"), filter.getNumeroAgencia()));
        }

        if (filter.getNumeroConta() != null && !filter.getNumeroConta().isBlank()) {
            predicates.add(cb.equal(root.get("numeroConta"), filter.getNumeroConta()));
        }

        if (filter.getNomeCorrentista() != null && !filter.getNomeCorrentista().isBlank()) {
            predicates.add(cb.equal(root.get("nomeCorrentista"), filter.getNomeCorrentista()));
        }

        if (filter.getSobrenomeCorrentista() != null && !filter.getSobrenomeCorrentista().isBlank()) {
            predicates.add(cb.equal(root.get("sobrenomeCorrentista"), filter.getSobrenomeCorrentista()));
        }

        if (filter.getDataInclusao() != null) {
            LocalDateTime startOfDay = filter.getDataInclusao().atStartOfDay();
            LocalDateTime endOfDay   = filter.getDataInclusao().atTime(23, 59, 59, 999999999);
            predicates.add(cb.between(root.get("dataHoraInclusao"), startOfDay, endOfDay));
        }

        if (filter.getDataInativacao() != null) {
            LocalDateTime startOfDay = filter.getDataInativacao().atStartOfDay();
            LocalDateTime endOfDay   = filter.getDataInativacao().atTime(23, 59, 59, 999999999);
            predicates.add(cb.between(root.get("dataHoraInativacao"), startOfDay, endOfDay));
        }

        //predicates.add(cb.isNull(root.get("dataHoraInativacao")));

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
