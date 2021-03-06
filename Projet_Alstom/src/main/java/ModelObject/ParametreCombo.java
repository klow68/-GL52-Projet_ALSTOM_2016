package ModelObject;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The Class ParametreCombo.
 */
public class ParametreCombo extends Parametre {

	/** The selects. */
	private ArrayList<Parametre> selects = null;

	/**
	 * Instantiates a new parametre combo.
	 *
	 * @param _id
	 *            the _id
	 * @param _classe
	 *            the _classe
	 * @param _label
	 *            the _label
	 * @param _type
	 *            the _type
	 * @param tableau
	 *            the tableau
	 */
	@SuppressWarnings("unchecked")
	public ParametreCombo(int _id, String _classe, String _label, typeParametre _type, JSONArray tableau) {
		super(_id, _classe, _label, _type);

		selects = new ArrayList<Parametre>();
		Iterator<JSONObject> ite = tableau.iterator();
		int id;
		String classe;
		String label;
		typeParametre typeFils;
		while (ite.hasNext()) {
			JSONObject job = ite.next();
			id = Integer.parseInt(job.get("id").toString());
			classe = job.get("class").toString();
			label = job.get("label").toString();
			typeFils = typeParametre.SELECT;
			JSONArray heritage = null;
			if (job.get("select") != null)
				heritage = (JSONArray) job.get("select");
			else
				heritage = new JSONArray();

			selects.add(new ParametreSelect(id, classe, label, typeFils, heritage));

		}

	}

	/**
	 * Adds the select.
	 *
	 * @param para
	 *            the para
	 */
	public void addSelect(Parametre para) {
		if (para.typePara.equals(typeParametre.SELECT)) {
			selects.add(para);
		} else {
			System.out.println("Erreur interne : mauvais select");
		}
	}

	/**
	 * Gets the selects.
	 *
	 * @return the selects
	 */
	public ArrayList<Parametre> getSelects() {
		return selects;
	}

	/**
	 * Gets the parametre.
	 *
	 * @param id
	 *            the id
	 * @return the parametre
	 */
	public Parametre getParametre(int id) {
		Parametre tmp = null;
		for (Parametre p : selects) {
			if (p.getId() == id) {
				tmp = p;
				return tmp;
			}

			if (p.getTypePara() == typeParametre.COMBO) {
				ParametreCombo pc = (ParametreCombo) p;

				tmp = pc.getParametre(id);
				if (tmp != null)
					return tmp;
			}
			if (p.getTypePara() == typeParametre.SELECT) {
				ParametreSelect pc = (ParametreSelect) p;

				tmp = pc.getParametre(id);
				if (tmp != null)
					return tmp;
			}

		}
		return tmp;
	}

}
