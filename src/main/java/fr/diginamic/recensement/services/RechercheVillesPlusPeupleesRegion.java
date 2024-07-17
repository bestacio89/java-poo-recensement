package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une région
 * donnée
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVillesPlusPeupleesRegion extends MenuService {

    @Override
    public void traiter(Recensement recensement, Scanner scanner) {

        try {
            System.out.println("Veuillez saisir un nom de région:");
            String nomRegion = scanner.nextLine().trim();

            if (nomRegion.isEmpty()) {
                System.out.println("Erreur : Veuillez saisir un nom de région.");
                return;
            }

            System.out.println("Veuillez saisir un nombre de villes:");
            String nbVillesStr = scanner.nextLine().trim();
            int nbVilles = Integer.parseInt(nbVillesStr);

            if (nbVilles <= 0) {
                System.out.println("Erreur : Veuillez saisir un nombre positif de villes.");
                return;
            }

            List<Ville> villesRegions = new ArrayList<>();

            List<Ville> villes = recensement.getVilles();
            for (Ville ville : villes) {
                if (ville.getNomRegion().toLowerCase().startsWith(nomRegion.toLowerCase())) {
                    villesRegions.add(ville);
                }
            }

            if (villesRegions.isEmpty()) {
                System.out.println("Aucune ville trouvée pour la région '" + nomRegion + "'.");
                return;
            }

            Collections.sort(villesRegions, new EnsemblePopComparateur(false));
            System.out.println("Les " + nbVilles + " villes les plus peuplées de la région " + nomRegion + " sont :");
            for (int i = 0; i < Math.min(nbVilles, villesRegions.size()); i++) {
                Ville ville = villesRegions.get(i);
                System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
            }

        } catch (NumberFormatException e) {
            System.err.println("Erreur : Veuillez saisir un nombre valide pour le nombre de villes.");
        } catch (Exception e) {
            System.err.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }
}
