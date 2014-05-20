/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.isel.mpd14.tohtml;

/**
 * @author Miguel Gamboa at CCISEL
 */
public class HtmlLeafElement implements HtmlNode {

  final String name;
  final String content;

  public HtmlLeafElement(String name) {
    this.name = name;
    content = null;
  }

  public HtmlLeafElement(String name, String content) {
    this.name = name;
    this.content = content;
  }

  public String print(int indent) {
    String res = calcIndent(indent) + "<" + name;
    res += (content != null ? " " : "") + content;
    res += " />\n";
    return res;
  }

}
