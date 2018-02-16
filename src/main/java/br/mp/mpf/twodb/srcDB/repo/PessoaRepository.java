package br.mp.mpf.twodb.srcDB.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.mp.mpf.twodb.srcDB.domain.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

}
