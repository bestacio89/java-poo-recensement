package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'une ville
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationVilleService extends MenuService {

    @Override
    public void traiter(Recensement rec, Scanner scanner) {

        try {
            System.out.println("Quel est le nom de la ville recherchée ? ");
            String choix = scanner.nextLine().trim();

            if (choix.isEmpty()) {
                System.out.println("Erreur : Le nom de la ville ne peut pas être vide.");
                return;
            }

            List<Ville> villes = rec.getVilles();
            boolean found = false;

            for (Ville ville : villes) {
                if (ville.getNom().equalsIgnoreCase(choix) || ville.getNom().toLowerCase().startsWith(choix.toLowerCase())) {
                    System.out.println(ville);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Aucune ville trouvée avec le nom '" + choix + "'.");
            }

        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }
}
