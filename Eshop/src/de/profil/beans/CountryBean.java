package de.profil.beans;

import java.util.Collection;

import de.profil.Country;

/**
 * 
 * @author Alexander Herrfurth
 * 
 */
public class CountryBean {
	
	// Fields
	
	private Country[] _countries = null;
	
	// Constructors
	
	public CountryBean(Country[]  countries) {
		
		_countries = countries;
		
	}
	
	public CountryBean() {
		
	}
	
	// Getters / Setters
	
	public void setCountries(Country[] countries) {
		
		_countries = countries;
		
	}

	// Methods
	
	/**
	 * @deprecated Ersetzt durch {@link SelectBoxBuilder}.
	 */
	@Deprecated
	public String htmlSelect(String name, int size, String defaultOption, String cssClass, String cssId) {
		
		String html = "";
		
		if(_countries == null)
			return html;
		
		size = Math.max(1, size);
		
		final String tab = "\t";
		final String newLine = "\n";
		
		html += "<select ";
		
		if(cssClass != null)
			html += "class='"+cssClass + "' ";
		
		if(cssId != null)
			html += "id='" + cssId + "' ";
		
		html += "name='" + name + "' size='" + size + "'>" + newLine;
		
		if(defaultOption != null)
			html += tab + "<option>" + defaultOption + "</option>" + newLine;
		
		for(Country c : _countries) {
			
			html += tab + "<option>" + c.getName() + "</option>" + newLine;
			
		}
		
		return html + "</select>" + newLine;
		
	}
	
	/**
	 * @deprecated Ersetzt durch {@link SelectBoxBuilder}.
	 */
	@Deprecated
	public String htmlSelect(String name, int size, String defaultOption, String cssClass) {
		return htmlSelect(name, size, defaultOption, cssClass, null);
	}
	
	/**
	 * @deprecated Ersetzt durch {@link SelectBoxBuilder}.
	 */
	@Deprecated
	public String htmlSelect(String name, int size, String defaultOption) {
		return htmlSelect(name, size, defaultOption, null, null);
	}
	
	public String htmlTable(String cssClass) {
		
		String html = "<table ";
		
		if(cssClass != null)
			html += " class='" + cssClass + "' ";
		
		html += " >\n<tr>";
		html += "<th>Land</th><th>Liefergebühr</th>";
		html += "</tr>\n";
		
		if(_countries != null) {
			
			for(Country c : _countries) {
				
				html += "<tr>";
				html += "<td>"+ c.getName() + "</td><td>" + String.format("%.2f EUR", c.getShippingFee()) + "</td>";
				html += "</tr>\n";
				
			}
			
		}
		
		html += "</table>\n";
		
		return html;
		
	}
		
}
