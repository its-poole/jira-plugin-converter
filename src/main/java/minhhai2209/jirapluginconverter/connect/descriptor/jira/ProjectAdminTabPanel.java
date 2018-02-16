package minhhai2209.jirapluginconverter.connect.descriptor.jira;

import java.util.Map;

import minhhai2209.jirapluginconverter.connect.descriptor.AbstractModule;
import minhhai2209.jirapluginconverter.connect.descriptor.UrlModule;

public class ProjectAdminTabPanel extends AbstractModule implements UrlModule {

  private String url;
  private Map<String, String> params;
  private int weight = 100;
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public Map<String, String> getParams() {
    return params;
  }
  public void setParams(Map<String, String> params) {
    this.params = params;
  }
  public int getWeight() {
    return weight;
  }
  public void setWeight(int weight) {
    this.weight = weight;
  }
  public boolean isAdminModule() {
    return true;
  }
}
