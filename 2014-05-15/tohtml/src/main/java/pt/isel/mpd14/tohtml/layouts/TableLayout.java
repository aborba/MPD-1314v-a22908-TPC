/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.isel.mpd14.tohtml.layouts;

import pt.isel.mpd14.probe.Binder;
import pt.isel.mpd14.tohtml.AbstractHtmlLayout;
import pt.isel.mpd14.tohtml.HtmlElement;

import java.util.Map;

/**
 * @author Miguel Gamboa at CCISEL
 */
public class TableLayout extends AbstractHtmlLayout {

  @Override
  protected String buildHeadContent(Object o) {
    String res = "    <title>";
    res += o.getClass();
    res += "</title>\n";
    return res;
  }

  @Override
  protected String buildBodyContent(Object o) {
    HtmlElement table = new HtmlElement("table");
    try {
      Map<String, Object> values = Binder.getFieldsValues(o);
      for (Map.Entry<String, Object> entry : values.entrySet()) {
        HtmlElement tr = new HtmlElement("tr");
        String k = entry.getKey();
        Object v = entry.getValue();
        tr.add(new HtmlElement("td", k));
        tr.add(new HtmlElement("td", v.toString()));
        table.add(tr);
      }
    } catch (IllegalArgumentException | IllegalAccessException ex) {
      throw new RuntimeException(ex);
    }
    return table.print();
  }

}
