package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import org.apache.commons.lang3.math.NumberUtils;

/** Recherche et affichage de la population d'un département
 *
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationDepartementService extends MenuService {

    @Override
    public void traiter(Recensement rec, Scanner scanner) {

        try {
            System.out.println("Quel est le code du département recherché ? ");
            String choix = scanner.nextLine();

            if (!NumberUtils.isDigits(choix)) {
                System.out.println("Erreur : Le code du département doit être composé uniquement de chiffres.");
                return;
            }

            List<Ville> villes = rec.getVilles();
            int somme = 0;
            for (Ville ville : villes) {
                if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
                    somme += ville.getPopulation();
                }
            }

            if (somme > 0) {
                System.err.println("Population du département " + choix + " : " + somme);
            } else {
                System.err.println("Département " + choix + " non trouvé.");
            }
        } catch (Exception e) {
            System.err.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }
}
