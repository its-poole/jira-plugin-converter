package minhhai2209.jirapluginconverter.converter.descriptor;

import minhhai2209.jirapluginconverter.plugin.descriptor.WebItemModule;
import minhhai2209.jirapluginconverter.connect.descriptor.Modules;
import minhhai2209.jirapluginconverter.connect.descriptor.webitem.WebItem;
import minhhai2209.jirapluginconverter.connect.descriptor.jira.ProjectAdminTabPanel;

public class JiraAdminTabPanelConverter extends ModuleConverter<WebItemModule, ProjectAdminTabPanel>{

  private static WebItemConverter webItemConverter = new WebItemConverter();

  @Override
  public WebItemModule toPluginModule(ProjectAdminTabPanel projectAdminTabPanel, Modules modules) {
    WebItem webItem = new WebItem();

    webItem.setLocation(projectAdminTabPanel.getLocation());
    webItem.setWeight(projectAdminTabPanel.getWeight());
    webItem.setUrl(projectAdminTabPanel.getUrl());
    webItem.setName(projectAdminTabPanel.getName());
    webItem.setConditions(projectAdminTabPanel.getConditions());
    webItem.setKey(projectAdminTabPanel.getKey());

    WebItemModule pluginModule = webItemConverter.toPluginModule(webItem, modules);

    return pluginModule;
  }
}
