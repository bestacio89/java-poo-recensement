package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Region;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Affichage des N régions les plus peuplées
 *
 * @author DIGINAMIC
 *
 */
public class RechercheRegionsPlusPeuplees extends MenuService {

    @Override
    public void traiter(Recensement recensement, Scanner scanner) {

        try {
            System.out.println("Veuillez saisir un nombre de régions:");
            String nbRegionsStr = scanner.nextLine();
            int nbRegions = Integer.parseInt(nbRegionsStr);

            // On vérifie que le nombre de régions saisi est positif
            if (nbRegions <= 0) {
                System.out.println("Erreur : Veuillez saisir un nombre positif de régions.");
                return;
            }

            // On récupère la liste des villes du recensement
            List<Ville> villes = recensement.getVilles();

            // On crée une HashMap pour stocker les régions
            // - Clé: nom de la région
            // - Valeur: instance de région
            Map<String, Region> mapRegions = new HashMap<>();

            // On parcourt les villes pour regrouper par région
            for (Ville ville : villes) {
                // On regarde si la région existe déjà dans la map
                Region region = mapRegions.get(ville.getNomRegion());

                // Si la région n'existe pas, on la créée
                if (region == null) {
                    region = new Region(ville.getCodeRegion(), ville.getNomRegion());
                    mapRegions.put(ville.getNomRegion(), region);
                }

                // On ajoute la ville à la région
                region.addVille(ville);
            }

            // On récupère la liste des régions à partir de la map
            List<Region> regions = new ArrayList<>(mapRegions.values());

            // On trie les régions par population décroissante
            Collections.sort(regions, new EnsemblePopComparateur(false));

            // On affiche les N premières régions
            int count = 0;
            for (Region region : regions) {
                System.out.println("Region " + region.getNom() + " : " + region.getPopulation() + " habitants.");
                count++;
                if (count >= nbRegions) {
                    break; // Sortir de la boucle une fois que les N premières régions ont été affichées
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Erreur : Veuillez saisir un nombre valide pour le nombre de régions.");
        } catch (Exception e) {
            System.err.println("Une erreur inattendue s'est produite : " + e.getMessage());
        }
    }
}
