package com.byteparity.forms.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "com.byteparity.forms.configuration.ViewFormEntriesConfiguration")
public interface ViewFormEntriesConfiguration {
	@Meta.AD(required = false)
    public String getHiddenFormField();
}
