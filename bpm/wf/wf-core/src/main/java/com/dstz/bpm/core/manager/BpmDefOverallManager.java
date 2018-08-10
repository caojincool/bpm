package com.dstz.bpm.core.manager;

import com.dstz.bpm.core.model.BpmOverallView;
import java.util.List;
import java.util.Map;

public abstract interface BpmDefOverallManager
{
  public abstract BpmOverallView getBpmOverallView(String paramString);
  
  public abstract void saveBpmOverallView(BpmOverallView paramBpmOverallView);
  
  public abstract Map<String, List<BpmOverallView>> importPreview(String paramString)
    throws Exception;
  
  public abstract void importSave(List<BpmOverallView> paramList);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\BpmDefOverallManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */