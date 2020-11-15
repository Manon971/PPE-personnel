package testsUnitaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import personnel.DroitsInsuffisants;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.ImpossibleDeSupprimerRoot;
import personnel.Ligue;
import personnel.SauvegardeImpossible;


class TestLigue 
{
	/*
	 * Initialisation des variables de la classe
	 */
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
	static final String PASSWORD = "azerty";
	
	/*
	 * Ce test permet de vérifier si la fonction AddLigue fonctionne
	 */
	@Test
	void testAddLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
		assertTrue(gestionPersonnel.getLigues().contains(ligue));
	}

	/*
	 * Ce test permet de vérifier si la fonction AddLigue fonctionne
	 * avec un Id
	 */
	@Test
	void testAddLigueById() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue(1, "Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
		//assertEquals(1, ligue.getId());
		assertTrue(gestionPersonnel.getLigues().contains(ligue));
	}
	
	/*
	 * Ce test permet de vérifier si la fonction AddEmploye fonctionne
	 */
	@Test
	void testAddEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", PASSWORD, "01/01/2010", "02/01/2020"); 
		assertEquals(employe, ligue.getEmployes().first());
	}
	
	/*
	 * Ce test permet de vérifier si la fonction GetLigues fonctionne
	 */
	@Test
	void testGetLigues() throws SauvegardeImpossible{
		gestionPersonnel.addLigue("Fléchettes");
		gestionPersonnel.addLigue("Foot");
		
		assertEquals(2, gestionPersonnel.getLigues().size());
	}

	/*
	 * Ce test permet de vérifier si la fonction AddLigue fonctionne
	 */
	@Test
	void testGetLigue() throws SauvegardeImpossible{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe administrateur = ligue.addEmploye("Admin", "Admin", "admin@gmail.com", PASSWORD, null, null); 
		ligue.setAdministrateur(administrateur);
		
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", PASSWORD, null, null);
		
		Ligue ligueByAdmin = gestionPersonnel.getLigue(administrateur);
		assertNotNull(ligueByAdmin);
		assertEquals(ligue, ligueByAdmin);
		
		ligueByAdmin = gestionPersonnel.getLigue(employe);
		assertNull(ligueByAdmin);
	}
	
	/*
	 * Ce test permet de vérifier si la fonction RemoveLigues fonctionne
	 */
	@Test
	void testRemoveLigues() throws SauvegardeImpossible{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
		ligue.remove();
		assertTrue(gestionPersonnel.getLigues().isEmpty());
	}
	
	/*
	 * Ce test permet de vérifier si la fonction SetAdministrateur fonctionne
	 */
	@Test
	void testSetAdministrateur() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Ligue ligue1 = gestionPersonnel.addLigue("Foot");
		Employe employe = ligue.addEmploye("toto", "toto", "toto@toto.com", PASSWORD, null, null);
		assertThrows(DroitsInsuffisants.class, () -> {
			ligue1.setAdministrateur(employe);
		});
	}
	
	
	
}
