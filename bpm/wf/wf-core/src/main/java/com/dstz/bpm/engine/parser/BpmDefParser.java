package com.dstz.bpm.engine.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.bpm.api.engine.plugin.def.BpmDef;

abstract interface BpmDefParser<D extends BpmDef>
{
  public abstract Object parseDef(D paramD, String paramString);
  
  public abstract void parse(D paramD, JSONObject paramJSONObject);
  
  public abstract String getKey();
  
  public abstract Class getClazz();
  
  public abstract boolean isArray();
  
  public abstract String validate(Object paramObject);
  
  public abstract void setDefParam(D paramD, Object paramObject);
  
  public abstract void JSONAmend(D paramD, Object paramObject, JSON paramJSON);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\parser\BpmDefParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */