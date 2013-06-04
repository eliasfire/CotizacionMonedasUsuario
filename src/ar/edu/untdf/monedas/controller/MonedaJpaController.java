/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.monedas.controller;

import ar.edu.untdf.monedas.controller.exceptions.NonexistentEntityException;
import ar.edu.untdf.monedas.modelos.Moneda;
import ar.edu.untdf.monedas.modelos.Moneda;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Matias
 */
public class MonedaJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MonedaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Moneda moneda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(moneda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Moneda moneda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            moneda = em.merge(moneda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = (long) moneda.getIdmoneda();
                if (findMoneda(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int codigo) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moneda moneda;
            try {
            	moneda = em.getReference(Moneda.class, codigo);
            	moneda.getIdmoneda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + codigo + " no longer exists.", enfe);
            }
            em.remove(moneda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Moneda> findMonedaEntities() {
        return findMonedaEntities(true, -1, -1);
    }

    public List<Moneda> findMonedaEntities(int maxResults, int firstResult) {
        return findMonedaEntities(false, maxResults, firstResult);
    }

    private List<Moneda> findMonedaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Moneda.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Moneda findMoneda(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Moneda.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonedaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Moneda> rt = cq.from(Moneda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


	public Image getImage(byte[] bytes, boolean isThumbnail) throws IOException {

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
		ImageReader reader = (ImageReader) readers.next();
		Object source = bis; // File or InputStream
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		if (isThumbnail) {

		param.setSourceSubsampling(4, 4, 0, 0);

		}
		return reader.read(0, param);

		}
	
	public Moneda findMonedaPorSiglas(String siglas) throws Exception {
        EntityManager em = getEntityManager();
        Moneda mon = null;
        try {
			System.out.println("consultado " + siglas);

            Query query = em.createNamedQuery("Moneda.findByMoneda");
            query.setParameter("siglas", siglas);

            return (Moneda) query.getSingleResult();
        } catch (NoResultException ne){
            throw new Exception("No se encontró Moneda: " + siglas);
        } catch (NonUniqueResultException ue){
            throw new Exception("La conincidencia para el Moneda: " + siglas + ", no es única");
        } finally {
            em.close();
        }
    }
    
}
