package de.profil.beans;

import java.util.Collection;

import de.profil.Country;

/**
 * 
 * @author Alexander Herrfurth
 * @deprecated Ersetzt durch {@link SelectBoxBuilder}.
 */
@Deprecated
public class CountryBean {
	
	// Fields
	
	private Collection<Country> _countries = null;
	
	// Constructors
	
	public CountryBean(Collection<Country> countries) {
		
		_countries = countries;
		
	}
	
	public CountryBean() {
		
	}

	// Methods
	
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
	
	public String htmlSelect(String name, int size, String defaultOption, String cssClass) {
		return htmlSelect(name, size, defaultOption, cssClass, null);
	}
	
	public String htmlSelect(String name, int size, String defaultOption) {
		return htmlSelect(name, size, defaultOption, null, null);
	}
		
}
