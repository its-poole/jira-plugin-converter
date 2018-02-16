package minhhai2209.jirapluginconverter.converter.descriptor;

import minhhai2209.jirapluginconverter.plugin.descriptor.WebItemModule;
import minhhai2209.jirapluginconverter.connect.descriptor.Modules;
import minhhai2209.jirapluginconverter.connect.descriptor.page.Page;
import minhhai2209.jirapluginconverter.connect.descriptor.jira.ProjectAdminTabPanel;

public class JiraAdminTabPanelConverter extends ModuleConverter<WebItemModule, ProjectAdminTabPanel>{

  private static PageConverter pageConverter = new PageConverter("alt.jira.project.config");

  @Override
  public WebItemModule toPluginModule(ProjectAdminTabPanel projectAdminTabPanel, Modules modules) {
    Page page = new Page();

    page.setLocation(projectAdminTabPanel.getLocation());
    page.setWeight(projectAdminTabPanel.getWeight());
    page.setUrl(projectAdminTabPanel.getUrl());
    page.setName(projectAdminTabPanel.getName());
    page.setConditions(projectAdminTabPanel.getConditions());
    page.setKey(projectAdminTabPanel.getKey());

    WebItemModule pluginModule = pageConverter.toPluginModule(page, modules);

    return pluginModule;
  }
}
