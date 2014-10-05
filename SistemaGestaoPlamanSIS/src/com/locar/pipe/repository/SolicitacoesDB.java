package com.locar.pipe.repository;

import java.io.Serializable;
import java.util.List;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.filtros.FiltrosSolicitacoes;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.SolicitacaoServico;

public interface SolicitacoesDB extends Serializable {

	void save(SolicitacaoServico solicitacao);
	void delete(SolicitacaoServico solicitacao);
	void update(SolicitacaoServico solicitacao);
	SolicitacaoServico findById(long id);
	List<SolicitacaoServico> getAll();
	long qntPorSetor(Departamento setor);
	long qntPorStatus(Status status);
	List<SolicitacaoServico> listarPorStatusSetor(Departamento setor,Status status);
	SolicitacaoServico jaExiste(SolicitacaoServico solicitacao);
	
	//pesquisas especias-----------------------
	List<SolicitacaoServico> listarPorNomeEquipamento(String query);
	List<SolicitacaoServico> pesquisarPorFiltros(FiltrosSolicitacoes filtro);
	List<SolicitacaoServico> pesquisarPorFiltrosSupervisor(FiltrosSolicitacoes filtro);
	
}
