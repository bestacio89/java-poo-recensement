package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées d'un département
 * donné
 *
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesDepartement extends MenuService {

    @Override
    public void traiter(Recensement recensement, Scanner scanner) {

        try {
            System.out.println("Veuillez saisir un numéro de département:");
            String nomDept = scanner.nextLine().trim();

            // Vérifier si le département existe dans le recensement
            boolean deptExiste = false;
            List<Ville> villes = recensement.getVilles();
            for (Ville ville : villes) {
                if (ville.getCodeDepartement().equalsIgnoreCase(nomDept)) {
                    deptExiste = true;
                    break;
                }
            }

            if (!deptExiste) {
                System.out.println("Erreur : Le département '" + nomDept + "' n'existe pas dans le recensement.");
                return;
            }

            System.out.println("Veuillez saisir un nombre de villes:");
            String nbVillesStr = scanner.nextLine().trim();
            int nbVilles = Integer.parseInt(nbVillesStr);

            if (nbVilles <= 0) {
                System.out.println("Erreur : Veuillez saisir un nombre positif de villes.");
                return;
            }

            List<Ville> villesDept = new ArrayList<>();

            // Filtrer les villes du département spécifié
            for (Ville ville : villes) {
                if (ville.getCodeDepartement().equalsIgnoreCase(nomDept)) {
                    villesDept.add(ville);
                }
            }

            // Trier les villes par population décroissante
            Collections.sort(villesDept, new EnsemblePopComparateur(false));

            // Afficher les N villes les plus peuplées du département
            if (!villesDept.isEmpty()) {
                System.out.println("Les " + nbVilles + " villes les plus peuplées du département " + nomDept + " :");
                int count = 0;
                for (int i = 0; i < villesDept.size() && count < nbVilles; i++) {
                    Ville ville = villesDept.get(i);
                    System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
                    count++;
                }
            } else {
                System.out.println("Aucune ville trouvée pour le département " + nomDept + ".");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez saisir un nombre valide pour le nombre de villes.");
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }
}
