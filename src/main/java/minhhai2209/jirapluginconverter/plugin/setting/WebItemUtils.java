package minhhai2209.jirapluginconverter.plugin.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minhhai2209.jirapluginconverter.connect.descriptor.Context;
import minhhai2209.jirapluginconverter.connect.descriptor.Modules;
import minhhai2209.jirapluginconverter.connect.descriptor.UrlModule;
import minhhai2209.jirapluginconverter.connect.descriptor.webitem.WebItem;
import minhhai2209.jirapluginconverter.connect.descriptor.webitem.WebItemTarget;
import minhhai2209.jirapluginconverter.connect.descriptor.webitem.WebItemTarget.Type;
import minhhai2209.jirapluginconverter.connect.descriptor.jira.ProjectAdminTabPanel;
import minhhai2209.jirapluginconverter.plugin.utils.EnumUtils;

public class WebItemUtils {

  private static Map<String, UrlModule> urlModuleLookup;

  public static void buildWebItemLookup() {
    Modules modules = PluginSetting.getModules();
    List<WebItem> webItems = modules.getWebItems();
    List<ProjectAdminTabPanel> jiraProjectAdminTabPanels = modules.getJiraProjectAdminTabPanels();
    urlModuleLookup = new HashMap<String, UrlModule>();

    if (webItems != null) {
      for (WebItem webItem : webItems) {
        String key = webItem.getKey();
        urlModuleLookup.put(key, webItem);
      }
    }
    if (jiraProjectAdminTabPanels != null) {
      for (ProjectAdminTabPanel projectAdminTabPanel: jiraProjectAdminTabPanels) {
        String key = projectAdminTabPanel.getKey();
        urlModuleLookup.put(key, projectAdminTabPanel);
      }
    }
  }

  public static String getFullUrl(UrlModule urlModule) {
    String webItemUrl = urlModule.getUrl();
    String baseUrl = PluginSetting.getPluginBaseUrl();

    if (webItemUrl.startsWith("http://") || webItemUrl.startsWith("https://")) {
      return webItemUrl;
    }

    if (urlModule instanceof WebItem) {
      Type type = Type.page;
      WebItem webItem = (WebItem)urlModule;
      Context context = webItem.getContext();
      WebItemTarget target = webItem.getTarget();

      if (context == null) {
        context = Context.addon;
      }
      if (target != null && target.getType() != null) {
        type = target.getType();
      }
      if (EnumUtils.equals(type, Type.page)) {
        switch (context) {
          case addon:
          case ADDON:
            baseUrl = PluginSetting.getPluginBaseUrl();
            break;
          case product:
          case PRODUCT:
            baseUrl = JiraUtils.getFullBaseUrl();
            break;
          case page:
          case PAGE:
            baseUrl = JiraUtils.getFullBaseUrl() + "/plugins/servlet/ac/" + PluginSetting.getDescriptor().getKey() + "/";
            break;
          default:
            throw new IllegalStateException();
        }
      } else {
        switch (context) {
          case page:
          case PAGE:
            baseUrl = PluginSetting.getPluginBaseUrl() + "/";
            break;
          default:
            baseUrl = PluginSetting.getPluginBaseUrl();
        }
      }
    }

    return baseUrl + webItemUrl;
  }

  public static UrlModule getWebItem(String key) {
    return urlModuleLookup.get(key);
  }
}
