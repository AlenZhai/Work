package com.livechain.pid.parameter;

import org.json.JSONObject;

public interface ParameterBuilder {
	public void buildQueryParams(JSONObject params ,JSONObject query);
	public void buildUpdateParams(JSONObject newPerson ,JSONObject oldPerson,boolean updateAll);
	public void buildDelParams(JSONObject params);
	public void buildAddParams(JSONObject params,boolean genpid);

}
