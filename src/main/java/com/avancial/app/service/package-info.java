/**
 * @author hamza.laterem
 *
 * Cette couche correspond à la partie fonctionnelle de l'application, celle qui implémente la « logique »,
 * et qui décrit les opérations que l'application opère sur les données en fonction des requêtes des utilisateurs, 
 * effectuées au travers de la couche présentation.
 * 
 * Les différentes règles de gestion et de contrôle du système sont mises en œuvre dans cette couche.
 * 
 * La couche métier offre des services applicatifs et métier2 à la couche présentation. Pour fournir ces services, 
 * elle s'appuie, le cas échéant, sur les données du système, accessibles au travers des services de la couche inférieure. 
 * En retour, elle renvoie à la couche présentation les résultats qu'elle a calculés.
 * 
 * @url https://fr.wikipedia.org/wiki/Architecture_trois_tiers
 *
 */
package com.avancial.app.service;