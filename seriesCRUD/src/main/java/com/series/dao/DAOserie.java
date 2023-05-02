package com.series.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.series.util.*;
import com.series.model.*;

import javax.swing.*;

public class DAOserie {
	
	public void insertSerie(Serie s) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.persist(s);
			transaction.commit();
			JOptionPane.showMessageDialog(null, "Serie insertada");
		} catch (Exception e) {
			if (transaction!=null) {
				JOptionPane.showMessageDialog(null, "Error al insertar serie");
				transaction.rollback();
			}
		}
	}
	
	public void updateSerie(Serie s) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.merge(s);
			transaction.commit();
			JOptionPane.showMessageDialog(null, "Serie actualizada");
		} catch (Exception e) {
			if (transaction!=null) {
				JOptionPane.showMessageDialog(null, "Error al actualizar serie");
				transaction.rollback();
			}
		}
	}
	
	public void deleteSerie(int id) {
		Transaction transaction=null;
		Serie s=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			s=session.get(Serie.class, id);
			session.remove(s);
			transaction.commit();
			JOptionPane.showMessageDialog(null, "Serie borrada");
		} catch (Exception e) {
			if (transaction!=null) {
				JOptionPane.showMessageDialog(null, "Error al borrar serie");
				transaction.rollback();
			}
		}
	}
	
	public Serie selectSerieById(int id) {
		Transaction transaction=null;
		Serie s=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			s=session.get(Serie.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				JOptionPane.showMessageDialog(null, "Error al seleccionar serie");
				transaction.rollback();
			}
		}
		return s;
	}
	
	public List<Serie> selectAllSeries() {
		Transaction transaction=null;
		List<Serie> series=null;
		Serie s=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			series=session.createQuery("from Serie",Serie.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
					JOptionPane.showMessageDialog(null, "Error al seleccionar series");
					transaction.rollback();
			}
		}
		return series;
	}

}
