package de.profil.beans;

public class SelectBoxBuilder {
	
	// Fields
	
	private ISelectBoxOption[] _options = null;
	private String _defaultText = null;
	private String _defaultValue = null;
	
	private String _preselectedValue = null;
	
	// Constructors
	
	public SelectBoxBuilder() {
		
	}
	
	public SelectBoxBuilder(ISelectBoxOption[] options) {
		
		_options = options;
		
	}
	
	// Getters / Setters
	
	public ISelectBoxOption[] getOptions() {
		return _options;
	}

	public void setOptions(ISelectBoxOption[] options) {
		_options = options;
	}

	public String getDefaultText() {
		return _defaultText;
	}

	public void setDefaultText(String defaultText) {
		_defaultText = defaultText;
	}

	public String getPreselectedValue() {
		return _preselectedValue;
	}

	public void setPreSelectedItem(String preselectedValue) {
		_preselectedValue = preselectedValue;
	}

	public String getDefaultValue() {
		return _defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		_defaultValue = defaultValue;
	}

	// Methods
	
	public String htmlSelect(String name, int size, String cssClass, String cssId) {
		
		String html = "";
		
		if(_options == null)
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
		
		if(_defaultText != null) {
			
			html += tab + "<option " + (_defaultValue != null ? "value='"+_defaultValue+"'" : "") + " >" + _defaultText;		
			html += "</option>" + newLine;
			
		}

		for(ISelectBoxOption opt : _options) {
			
			String selected = opt.getOptionValue().equals(_preselectedValue) ? " selected " : "";
			html += tab + "<option value='" + opt.getOptionValue() + "' " + selected + " >" + opt.getOptionText() + "</option>" + newLine;
			
		}
		
		return html + "</select>" + newLine;
		
	}
	
	public String htmlSelect(String name, int size, String cssClass) {
		return htmlSelect(name, size, cssClass, null);
	}
	
	public String htmlSelect(String name, int size) {
		return htmlSelect(name, size, null, null);
	}

}
