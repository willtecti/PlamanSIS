package com.locar.pipe.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.enuns.TipoDeOrdem;
import com.locar.pipe.interfaces.ColecaoOsInterface;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.util.HibernateUtil;

public class ColecaoOsRepository implements ColecaoOsInterface {
	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(OrdemServico ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.save(ordemCorretiva);

	}

	@Override
	public void excluir(OrdemServico ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.delete(ordemCorretiva);

	}

	@Override
	public void editar(OrdemServico ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.update(ordemCorretiva);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listarTodasCorretiva() {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		List<OrdemServico> corretivas = new ArrayList<OrdemServico>();
		corretivas = session.createCriteria(OrdemServico.class)
				.add(Restrictions.eq("tipo", TipoDeOrdem.CORRETIVA)).list();

		return corretivas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listarTodasPreventivas() {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		List<OrdemServico> preventivas = new ArrayList<OrdemServico>();
		preventivas = session.createCriteria(OrdemServico.class)
				.add(Restrictions.eq("tipo", TipoDeOrdem.PREVENTIVA)).list();

		return preventivas;
	}

	@Override
	public OrdemServico buscarPorId(long id) {
		OrdemServico ordem = null;
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		ordem = (OrdemServico) session.get(OrdemServico.class, id);

		return ordem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listarTodas() {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		return session.createCriteria(OrdemServico.class)
				.add(Restrictions.eq("tipo", TipoDeOrdem.CORRETIVA)).list();
	}

	@Override
	public long qntDeOrdemPorSetor(Departamento setor) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		Criteria criteria = session.createCriteria(OrdemServico.class);
		criteria.add(Restrictions.eq("setor", setor));
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}
}
