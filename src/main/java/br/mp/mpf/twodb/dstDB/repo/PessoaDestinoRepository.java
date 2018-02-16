package br.mp.mpf.twodb.dstDB.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.mp.mpf.twodb.dstDB.domain.PessoaDestino;

@Repository
public interface PessoaDestinoRepository extends CrudRepository<PessoaDestino,Long>{

}
