package com.dstz.bpm.core.manager;

import com.dstz.bpm.core.model.BpmOverallView;
import java.util.List;
import java.util.Map;

public interface BpmDefOverallManager {
	public BpmOverallView getBpmOverallView(String var1);

	public void saveBpmOverallView(BpmOverallView var1);

	public Map<String, List<BpmOverallView>> importPreview(String var1) throws Exception;

	public void importSave(List<BpmOverallView> var1);
}