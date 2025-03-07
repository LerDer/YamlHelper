package com.wd.yh.config;

import com.intellij.openapi.options.Configurable;
import javax.swing.JComponent;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

/**
 * @author lww
 * @date 2025-01-23 13:14
 */
public class YamlHelperConfigurable implements Configurable {

	private YamlHelperConfigUI yamlHelperConfigUI;

	@Override
	public String getDisplayName() {
		return "Yaml Helper";
	}

	@Override
	public @Nullable JComponent createComponent() {
		if (yamlHelperConfigUI == null) {
			yamlHelperConfigUI = new YamlHelperConfigUI();
		}
		return yamlHelperConfigUI.createComponent();
	}

	@Override
	public void reset() {
		if (yamlHelperConfigUI != null) {
			yamlHelperConfigUI.reset();
		}
	}

	@Override
	public boolean isModified() {
		return yamlHelperConfigUI != null && yamlHelperConfigUI.isModified();
	}

	@Override
	public void apply() {
		if (yamlHelperConfigUI != null) {
			yamlHelperConfigUI.apply();
		}
	}

	@Override
	public void disposeUIResources() {
		yamlHelperConfigUI = null;
	}

	@Override
	public @Nullable @NonNls String getHelpTopic() {
		return "";
	}

}
