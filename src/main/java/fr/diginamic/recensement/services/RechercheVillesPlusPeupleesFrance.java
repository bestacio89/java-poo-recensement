package fr.diginamic.recensement.services;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées de France
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesFrance extends MenuService {

    @Override
    public void traiter(Recensement recensement, Scanner scanner) {

        try {
            System.out.println("Veuillez saisir un nombre de villes:");
            String nbVillesStr = scanner.nextLine().trim();
            int nbVilles = Integer.parseInt(nbVillesStr);

            if (nbVilles <= 0) {
                System.out.println("Erreur : Veuillez saisir un nombre positif de villes.");
                return;
            }

            List<Ville> villes = recensement.getVilles();
            if (nbVilles > villes.size()) {
                System.out.println("Attention : Le nombre de villes demandé dépasse le nombre total de villes dans le recensement.");
                nbVilles = villes.size(); // Réduire nbVilles pour éviter l'index out of bounds
            }

            System.out.println("Les " + nbVilles + " villes les plus peuplées de France sont :");
            Collections.sort(villes, new EnsemblePopComparateur(false));
            for (int i = 0; i < nbVilles; i++) {
                Ville ville = villes.get(i);
                System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez saisir un nombre valide pour le nombre de villes.");
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }
}
