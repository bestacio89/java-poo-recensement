package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'une région
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationRegionService extends MenuService {

    @Override
    public void traiter(Recensement rec, Scanner scanner) {

        try {
            System.out.println("Quel est le nom (ou le début de nom) de la région recherchée ? ");
            String choix = scanner.nextLine().trim();

            if (choix.isEmpty()) {
                System.out.println("Erreur : Le nom de la région ne peut pas être vide.");
                return;
            }

            List<Ville> villes = rec.getVilles();
            int somme = 0;
            String nom = null;

            for (Ville ville : villes) {
                if (ville.getNomRegion().toLowerCase().startsWith(choix.toLowerCase())
                    || ville.getCodeRegion().toLowerCase().equals(choix.toLowerCase())) {
                    somme += ville.getPopulation();
                    nom = ville.getNomRegion();
                }
            }

            if (somme > 0) {
                System.out.println("Population de la région " + nom + " : " + somme);
            } else {
                System.out.println("Région " + choix + " non trouvée.");
            }

        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }
}
