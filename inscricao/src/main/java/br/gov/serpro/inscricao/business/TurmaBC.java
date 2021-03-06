package br.gov.serpro.inscricao.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.inscricao.TurmaException;
import br.gov.serpro.inscricao.config.InscricaoConfig;
import br.gov.serpro.inscricao.entity.Aluno;

@BusinessController
public class TurmaBC implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private InscricaoConfig config;

	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	MessageContext messageContext;	

	/**
	 * Essa anotação abre uma transação com o BD antes da execução do matricular
	 * É finalizada após seu término, caso aconteça a uma exceção será feito um
	 * rolback automaticamente Essa anotação delega o controle para o
	 * EntityManager "JPA"
	 */
	@Transactional
	@RequiredPermission(resource = "aluno", operation = "matricular")
	public void matricular(Aluno aluno) {
		if (estaMariculado(aluno)
				|| obterAlunosMatriculados().size() == 5) {
			throw new TurmaException();
		}
		alunoBC.insert(aluno);	
		String mensagem = bundle.getString("matricula.sucesso", aluno.getNome());
		logger.info(mensagem);
		messageContext.add(mensagem);
	}

	@RequiredPermission(resource="aluno", operation="consultar")
	public boolean estaMariculado(Aluno aluno) {
		return obterAlunosMatriculados().contains(aluno);
	}

	@ExceptionHandler
	public void tratar(TurmaException e) {
		logger.warn(bundle.getString("matricula.erro"));
		throw e;
	}
	
	public List<Aluno> obterAlunosMatriculados() {
		return alunoBC.findAll();
	}

	@Startup
	public void iniciar() {
		logger.info("Iniciando ...");
	}

}
