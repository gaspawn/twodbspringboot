package br.mp.mpf.twodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import br.mp.mpf.twodb.dstDB.domain.PessoaDestino;
import br.mp.mpf.twodb.dstDB.repo.PessoaDestinoRepository;
import br.mp.mpf.twodb.srcDB.repo.PessoaRepository;

@SpringBootApplication
@EnableAutoConfiguration
@Import(value = { ConfigSrcDB.class, ConfigDstDB.class })
public class TwodbApplication implements CommandLineRunner {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	PessoaDestinoRepository pessoaDestinoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TwodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		pessoaDestinoRepository.save(new PessoaDestino());
	}
}
