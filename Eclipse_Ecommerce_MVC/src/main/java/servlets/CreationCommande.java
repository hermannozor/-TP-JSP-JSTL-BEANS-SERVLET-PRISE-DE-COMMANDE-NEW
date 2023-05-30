package servlets;
import forms.CreationCommandeForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import beans.Commande;
import beans.Client;

/**
 * Servlet implementation class CreationCommande
 */
public class CreationCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   public static final String ATT_COMMANDE = "commande";
	    public static final String ATT_FORM     = "form";

	    public static final String VUE_SUCCES   = "/WEB-INF/afficherCommande.jsp";
	    public static final String VUE_FORM     = "/WEB-INF/creerCommande.jsp";

	    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        /* À la réception d'une requête GET, simple affichage du formulaire */
	        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
	    }

	    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        /* Préparation de l'objet formulaire */
	        CreationCommandeForm form = new CreationCommandeForm();

	        /* Traitement de la requête et récupération du bean en résultant */
	        Commande commande = form.creerCommande( request );

	        /* Ajout du bean et de l'objet métier à l'objet requête */
	        request.setAttribute( ATT_COMMANDE, commande );
	        request.setAttribute( ATT_FORM, form );

	        if ( form.getErreurs().isEmpty() ) {
	            /* Si aucune erreur, alors affichage de la fiche récapitulative */
	            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
	        } else {
	            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
	            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
	        }
	    }
}
